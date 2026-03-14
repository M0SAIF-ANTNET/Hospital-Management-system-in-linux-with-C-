#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <pwd.h>
#include <grp.h>
#include <sys/stat.h>
#include <dirent.h>
#include <fcntl.h>
#include <errno.h>

#define BASE "/hospital"
#define ADMIN_DIR "/hospital/admin"
#define DOCTORS_DIR "/hospital/doctors"
#define PATIENTS_DIR "/hospital/patients"

typedef enum { ROLE_NONE, ROLE_ADMIN, ROLE_DOCTOR, ROLE_PATIENT } Role;

Role role;
uid_t uid;

int valid_name(const char *s) {
    if(!s || !*s) return 0;
    for(int i=0; s[i]; i++)
        if(!(isalnum(s[i]) || s[i]=='_' || s[i]=='-')) return 0;
    return 1;
}

Role detect_role() {
    uid = getuid();
    struct passwd *pw = getpwuid(uid);
    if(!pw) return ROLE_NONE;

    if(strcmp(pw->pw_name, "admin") == 0) return ROLE_ADMIN;

    int ngroups = 0;
    getgroups(0, NULL);
    gid_t groups[32];
    ngroups = getgroups(32, groups);

    for(int i=0;i<ngroups;i++) {
        struct group *gr = getgrgid(groups[i]);
        if(!gr) continue;
        if(strcmp(gr->gr_name,"doctors")==0) return ROLE_DOCTOR;
        if(strcmp(gr->gr_name,"patients")==0) return ROLE_PATIENT;
    }

    return ROLE_NONE;
}

int inside_base(const char *path) {
    char real[PATH_MAX];
    if(!realpath(path, real)) return 0;
    return strncmp(real, BASE, strlen(BASE)) == 0;
}

void list_dir(const char *path) {
    DIR *d = opendir(path);
    if(!d) return;
    struct dirent *e;
    while((e = readdir(d)))
        if(strcmp(e->d_name,".") && strcmp(e->d_name,".."))
            printf("%s\n", e->d_name);
    closedir(d);
}

void make_dir(const char *parent) {
    char name[64];
    scanf("%63s", name);
    if(!valid_name(name)) return;

    char path[256];
    snprintf(path,sizeof(path),"%s/%s",parent,name);
    mkdir(path,0750);
}

void remove_dir(const char *parent) {
    char name[64];
    scanf("%63s", name);
    if(!valid_name(name)) return;

    char path[256];
    snprintf(path,sizeof(path),"%s/%s",parent,name);
    rmdir(path);
}

void copy_file() {
    char src[256], dst[256];
    scanf("%255s %255s", src, dst);
    if(!inside_base(src) || !inside_base(dst)) return;

    int in = open(src,O_RDONLY);
    int out = open(dst,O_WRONLY|O_CREAT|O_TRUNC,0640);
    if(in<0 || out<0) return;

    char buf[4096];
    ssize_t r;
    while((r=read(in,buf,sizeof(buf)))>0)
        write(out,buf,r);

    close(in);
    close(out);
}

void move_file() {
    char src[256], dst[256];
    scanf("%255s %255s", src, dst);
    if(!inside_base(src) || !inside_base(dst)) return;
    rename(src,dst);
}

void add_note() {
    int fd = open(PATIENTS_DIR"/notes.txt",O_WRONLY|O_CREAT|O_APPEND,0640);
    if(fd<0) return;

    char buf[256];
    fgets(buf,sizeof(buf),stdin);
    write(fd,buf,strlen(buf));
    close(fd);
}

void create_patient_file() {
    char name[64];
    scanf("%63s", name);
    if(!valid_name(name)) return;

    char path[256];
    snprintf(path,sizeof(path),"%s/%s",PATIENTS_DIR,name);
    open(path,O_CREAT|O_EXCL,0640);
}

void view_file() {
    char path[256];
    scanf("%255s", path);
    if(!inside_base(path)) return;

    int fd = open(path,O_RDONLY);
    if(fd<0) return;

    char buf[4096];
    ssize_t r;
    while((r=read(fd,buf,sizeof(buf)))>0)
        write(1,buf,r);
    close(fd);
}

int main() {
    role = detect_role();
    int c;

    do {
        printf("\n1 admin\n2 doctors\n3 patients\n4 mkdir doctors\n5 rmdir doctors\n6 copy\n7 move\n8 note\n9 create patient file\n10 view\n0 exit\n");
        scanf("%d",&c);
        getchar();

        switch(c) {
            case 1: if(role==ROLE_ADMIN) list_dir(ADMIN_DIR); break;
            case 2: if(role!=ROLE_NONE) list_dir(DOCTORS_DIR); break;
            case 3: if(role!=ROLE_NONE) list_dir(PATIENTS_DIR); break;
            case 4: if(role==ROLE_ADMIN||role==ROLE_DOCTOR) make_dir(DOCTORS_DIR); break;
            case 5: if(role==ROLE_ADMIN||role==ROLE_DOCTOR) remove_dir(DOCTORS_DIR); break;
            case 6: if(role==ROLE_ADMIN||role==ROLE_DOCTOR) copy_file(); break;
            case 7: if(role==ROLE_ADMIN||role==ROLE_DOCTOR) move_file(); break;
            case 8: if(role==ROLE_DOCTOR||role==ROLE_PATIENT) add_note(); break;
            case 9: if(role==ROLE_PATIENT) create_patient_file(); break;
            case 10: if(role!=ROLE_NONE) view_file(); break;
        }

    } while(c!=0);

    return 0;
}