# ÜretimNot Android APK

Telefon üzerinden GitHub Actions ile APK üretmek için hazırlanmış Android kaynak projesi.

1. GitHub hesabında yeni bir **public veya private** depo oluştur (ör. `UretimNot`).
2. ZIP dosyasını telefonda çıkar. ZIP içindeki **tüm dosya ve klasörleri** deponun köküne yükle. `.github/workflows/android.yml` yolunu koru. GitHub mobil web arayüzünde gizli `.github` klasörünü yüklemek zor olursa dosyaları GitHub'ın web dosya yükleme arayüzü veya GitHub destekli bir dosya yöneticisi ile yükle.
3. GitHub > depo > **Actions** > **Android APK olustur** > **Run workflow**. İlk push işlemi de derlemeyi başlatabilir.
4. İşlem başarılı olduğunda çalıştırma sayfasının **Artifacts** bölümünden `UretimNot-APK` dosyasını indir; ZIP'i açıp `app-debug.apk` dosyasını kur.
5. Android izin isterse yalnızca güvendiğin bu APK için **Bilinmeyen uygulamaları yükle** iznini ver. Uygulama açıldığında **Bildirim izni** ver.

**Önemli:** APK bu dosyanın içinde bulunmaz; GitHub Actions derlemesi gerekir. İlk kurulum sonrası bildirim zamanlaması oluşturulur. 07:45 alarmı Android'de alarm simgesi gösterebilir. Telefonun güç tasarrufu ve üretici ayarları bildirimi etkileyebilir. Görevler telefonda yerel saklanır; uygulama kaldırıldığında silinebilir. İlk sürüm yedekleme/senkronizasyon içermez. Web sürümünde kayıtlı görevler otomatik aktarılmaz. Bu bir debug APK'dır, mağaza dağıtımı için imzalı release sürümü gerekir.
