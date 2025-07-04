# 🔐 Personal Security Dashboard

A centralized web application that empowers users to **monitor**, **manage**, and **secure** their authentication and login activity across applications — featuring JWT session tracking, OAuth2 login monitoring, 2FA setup, and real-time activity logs.

---

## 📌 Table of Contents

- [🔍 Project Overview](#-project-overview)
- [🚨 Problem Statement](#-problem-statement)
- [✅ Features](#-features)
- [🎯 Target Users](#-target-users)
- [⚙️ Tech Stack](#️-tech-stack)
- [🗂 Folder Structure](#-folder-structure)
- [📄 License](#-license)

---

## 🔍 Project Overview

The **Personal Security Dashboard** solves a common modern-day problem — the lack of centralized visibility and control over a user’s online authentication footprint. It lets users:

- Track JWT and OAuth2 tokens
- Monitor login history and devices
- Set up 2FA
- Revoke tokens or sessions
- Detect suspicious activity
- View security scores based on behavior

It’s ideal for privacy-conscious users, developers, and applications where authentication security is critical.

---

## 🚨 Problem Statement

In today’s digital landscape, users log in across multiple platforms using passwords, JWTs, OAuth2 providers, and 2FA. However, they often lack:

- Visibility over active sessions/tokens
- Alerts on suspicious activity
- Breach exposure awareness
- A centralized place to manage security settings

This results in **security risks**, **delayed breach detection**, and **poor account hygiene**.

---

## ✅ Features

| Feature                           | Description |
|-----------------------------------|-------------|
| 🔐 JWT & OAuth2 Manager           | Inspect, revoke, or refresh tokens. |
| 🌍 Login Device History           | IP-based login tracking with timestamps and device info. |
| 📲 Two-Factor Authentication (2FA)| Enable 2FA via OTP or Authenticator apps. |
| 🔁 Session Control                | End sessions remotely, logout from other devices. |
| 📊 Security Score                 | Calculates your security posture based on activity & settings. |
| 🛡️ Suspicious Activity Alerts    | Alerts for failed/suspicious login attempts. |
| 🔎 Breach Scanner (API-based)     | Check if your credentials were leaked in known breaches. |
| 🛠 Security Settings              | Control password policies, enable/disable 2FA, manage OAuth logins. |

---

## 🎯 Target Users

- Developers integrating **Spring Security**, JWT, and OAuth2
- Privacy-aware users wanting control over their account security
- Teams building **secure authentication flows**
- FinTech, SaaS, and Banking applications

---

## ⚙️ Tech Stack

| Layer       | Technology |
|-------------|------------|
| Backend     | Spring Boot, Spring Security, JWT, OAuth2 |
| Frontend    | React.js|
| Database    | MongoDB / PostgreSQL |
| Caching     | Redis |
| Security    | 2FA (TOTP), Email Verification, Token Blacklisting |
| APIs        | HaveIBeenPwned (optional), IP Geolocation |

---

## 🗂 Folder Structure (Backend)
com.securitydashboard
├── auth
│   ├── controller
│   ├── service
│   ├── dto
│   ├── entity
│   └── jwt
├── security
│   ├── config
│   ├── filter
│   ├── utils
│   └── 2fa
├── dashboard
│   ├── controller
│   ├── service
│   └── entity
├── logs
│   └── model / repository
├── breachcheck
│   └── integration
└── utils

📄 License
Let me know if you want me to:

- Generate a **live project description website (GitHub Pages)**  
- Create a **frontend README** separately  
- Add **badges**, **screenshots**, or **usage instructions** to this README  
- Package this into a **starter GitHub repo template**

Just say the word and I’ll prep it.
