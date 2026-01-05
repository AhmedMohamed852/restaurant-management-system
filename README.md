🍽️ Restaurant Management System
📌 وصف المشروع

Restaurant Management System هو نظام متكامل لإدارة المطاعم مبني باستخدام
Spring Boot في الـ Back-End و Angular في الـ Front-End.

المشروع بيوفر بنية حقيقية قريبة من سوق العمل، وبيغطي معظم السيناريوهات الأساسية لإدارة مطعم، مع التركيز على:

Clean Architecture

Separation of Concerns

Best Practices في Spring Boot و Angular

المشروع مناسب للتعلّم، التدريب العملي، وبناء Portfolio قوي لمطوري Full Stack.

🎯 المميزات الرئيسية

✅ إدارة المنتجات (Products)

✅ إدارة الأقسام (Categories)

✅ إدارة الطلبات (Orders)

✅ فلترة وترتيب سجل الطلبات (Orders History Filtering & Sorting)

✅ الموافقة على الطلبات (Order Approval Flow)

✅ إدارة المستخدمين والصلاحيات (Users & Roles)

✅ بيانات التواصل مع العملاء (Contact Info)

✅ توثيق كامل للـ APIs باستخدام Swagger

✅ Pagination للبيانات

✅ Exception Handling مركزي

✅ Authentication & Authorization

🛠️ التقنيات المستخدمة
🔹 Back-End

Java

Spring Boot

Spring Data JPA

Spring Security

Hibernate

Maven

Swagger (OpenAPI)

🔹 Front-End

Angular 15

TypeScript

HTML / CSS

RxJS

🔹 Database

MySQL

PostgreSQL (اختياري – حسب الإعداد)

🔹 Tools

Git & GitHub

Git LFS (لإدارة الملفات الكبيرة)

📂 هيكل المشروع
restaurant-management-system/
│
├── Back-End/
│   └── restaurant_Management_System/
│
├── Front-End/
│   └── restaurant-app/
│
├── .gitignore
├── .gitattributes
└── README.md

▶️ تشغيل المشروع محليًا
1️⃣ Back-End (Spring Boot)
🎯 الهدف

تشغيل السيرفر المسؤول عن:

Business Logic

Database

REST APIs

Authentication & Authorization

✅ الطريقة الأولى (مفضلة – باستخدام IDE)

افتح IntelliJ IDEA أو Eclipse

افتح المشروع من:

Back-End/restaurant_Management_System


ابحث عن الكلاس اللي يحتوي على:

@SpringBootApplication


اضغط Run

السيرفر هيشتغل على:

http://localhost:8081

✅ الطريقة الثانية (باستخدام Terminal)

افتح Terminal داخل مجلد الباك إند:

cd Back-End/restaurant_Management_System


لو بتستخدم Maven Wrapper:

./mvnw spring-boot:run


أو لو Maven مثبت:

mvn spring-boot:run

📑 Swagger API Documentation

بعد تشغيل الـ Back-End، تقدر تشوف كل الـ APIs موثقة من خلال Swagger:

http://localhost:8081/v3/api-docs


أو:

http://localhost:8081/swagger-ui/index.html

2️⃣ Front-End (Angular)
🎯 الهدف

تشغيل واجهة المستخدم الخاصة بالمطعم والتعامل مع الـ APIs.

افتح Terminal داخل مجلد الفرونت:

cd Front-End/restaurant-app


ثبت المكتبات (أول مرة فقط):

npm install


شغّل المشروع:

ng serve


افتح المتصفح على:

http://localhost:4200

⚠️ ملاحظات مهمة

يجب تشغيل Back-End أولًا قبل Front-End

تأكد من ضبط Base URL للـ APIs داخل Angular Services

مجلد node_modules غير مرفوع على GitHub (موجود في .gitignore)

تأكد من إعداد Database Connection في application.properties

📦 Git LFS

تم استخدام Git LFS لإدارة الملفات الكبيرة مثل:

.mp4

.zip

.jar

👨‍💻 صاحب المشروع

Ahmed Mohamed

🔗 GitHub Repository:
👉 https://github.com/AhmedMohamed852/restaurant-management-system