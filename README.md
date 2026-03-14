# Hospital Management System (Linux - C)

A secure command-line Hospital Management System written in C for Linux environments.  
The project demonstrates real operating system concepts such as user roles, permissions, file system security, and safe system-level programming.

This project is intended for **Operating Systems**, **Linux**, and **Security** practice.

---

## Features

- Role-based access control (Admin / Doctor / Patient)
- Real Linux users and groups (UID / GID based)
- Secure file and directory operations
- No system() or popen()
- No command injection
- Path traversal protection
- File sandboxing inside `/hospital`
- Uses POSIX system calls only
- Clean and minimal C design

---

## Roles

| Role     | Permissions |
|---------|-------------|
| Admin   | Full access, manage doctors directories |
| Doctor  | Manage doctor directories, add notes |
| Patient | Create files, add notes, view allowed files |

---

## Requirements

- Linux OS
- GCC compiler
- Root access (for initial setup only)

---

## Installation & Setup

### Install GCC

```bash
gcc --version
```

If not installed:

```bash
sudo apt install build-essential
```

---

### Create Hospital Directories

```bash
sudo mkdir /hospital
sudo mkdir /hospital/admin
sudo mkdir /hospital/doctors
sudo mkdir /hospital/patients

sudo chmod 750 /hospital
sudo chmod 750 /hospital/admin
sudo chmod 770 /hospital/doctors
sudo chmod 770 /hospital/patients
```

---

### Create Users and Groups

```bash
sudo groupadd doctors
sudo groupadd patients

sudo useradd -m admin
sudo useradd -m doctor1
sudo useradd -m patient1

sudo usermod -aG doctors doctor1
sudo usermod -aG patients patient1
```

Set passwords:

```bash
sudo passwd admin
sudo passwd doctor1
sudo passwd patient1
```

---

### Set Ownership

```bash
sudo chown admin:admin /hospital/admin
sudo chown admin:doctors /hospital/doctors
sudo chown admin:patients /hospital/patients
```

---

## Build

From the project root directory:

```bash
gcc src/hospital.c -o hospital -std=c11 -Wall -Wextra
```

---

## Run

### Admin

```bash
su - admin
./hospital
```

### Doctor

```bash
su - doctor1
./hospital
```

### Patient

```bash
su - patient1
./hospital
```

---

## Menu Options

```
1  List admin directory
2  List doctors directory
3  List patients directory
4  Create doctors directory
5  Remove doctors directory
6  Copy file
7  Move file
8  Add note
9  Create patient file
10 View file
0  Exit
```

---

## Security Design

- No shell execution  
- No command parsing  
- All file paths are restricted to `/hospital`  
- Input validation for file and directory names  
- Uses `open`, `read`, `write`, `mkdir`, `rename`  
- Uses real Linux permissions and groups  

---

## Testing

### Path Traversal Protection

```
../../etc/passwd
```

Blocked.

### Command Injection Attempts

```
test; rm -rf /
```

Rejected.

---

## Educational Value

This project demonstrates:

- Linux user/group permissions  
- Secure system programming in C  
- RBAC (Role-Based Access Control)  
- File system sandboxing  
- OS-level security concepts  

---

## Disclaimer

This project is for educational purposes only and should not be used in production environments.
