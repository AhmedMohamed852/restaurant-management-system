# Restaurant Management System

## وصف المشروع
نظام إدارة مطعم متكامل يعتمد على **Spring Boot** في الباك إند و **Angular** في الفرونت إند.  
يتيح المشروع إدارة:
- المنتجات
- الأقسام
- الطلبات
- المستخدمين
- بيانات التواصل مع العملاء

تم تصميم المشروع ليكون مناسب للتعلّم والتطبيق العملي على مشاريع Full Stack.

---

## التقنيات المستخدمة
- **Back-End:** Java – Spring Boot
- **Front-End:** Angular 15
- **Database:** MySQL أو PostgreSQL (حسب الإعداد)
- **Build Tool:** Maven
- **Version Control:** Git & GitHub
- **Git LFS:** لإدارة الملفات الكبيرة

---

## هيكل المشروع
restaurant-management-system/
├── Back-End/
│ └── restaurant_Management_System/
├── Front-End/
│ └── restaurant-app/
├── .gitignore
├── .gitattributes
└── README.md  


---

## طريقة تشغيل المشروع محليًا

## 1️⃣ Back-End (Spring Boot)

### الهدف  
تشغيل السيرفر المحلي الذي يتعامل مع قاعدة البيانات ويرسل البيانات للفرونت إند.

---

### ✅ الطريقة الأولى: باستخدام IDE (مفضلة)

1. افتح برنامج **IntelliJ IDEA** أو **Eclipse**
2. افتح المشروع من المسار:
3. ابحث عن الكلاس الرئيسي الذي يحتوي على:
```java
@SpringBootApplication

4. اضغط Run
5. السيرفر سيعمل على:

http://localhost:8081  


افتح Terminal داخل مجلد الباك إند:

cd Back-End/restaurant_Management_System


شغّل المشروع بإحدى الطريقتين:

لو بتستخدم Maven Wrapper:

./mvnw spring-boot:run


أو لو Maven مثبت على جهازك:

mvn spring-boot:run

2️⃣ Front-End (Angular)
الهدف

تشغيل واجهة المستخدم الخاصة بالمطعم.

افتح Terminal داخل مجلد الفرونت:

cd Front-End/restaurant-app


ثبّت المكتبات (أول مرة فقط):

npm install


شغّل المشروع:

ng serve


افتح المتصفح على:

http://localhost:4200


ملاحظات مهمة

تأكد أن Back-End شغال قبل Front-End

لو الفرونت بيطلب API، عدّل URL في ملفات الـ services لو لزم الأمر

مجلد node_modules غير مرفوع على GitHub (موجود في .gitignore)

Git LFS

تم استخدام Git LFS لإدارة الملفات الكبيرة مثل:

.mp4

.zip

.jar

صاحب المشروع

Ahmed Mohamed
GitHub Repository:
https://github.com/AhmedMohamed852/restaurant-management-system

