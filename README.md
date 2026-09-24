# Final_Project
Web UI automation testing and API testing.

## Cara menjalankan

```bash
# semua test (API + Web UI)
./gradlew test

# hanya API
./gradlew test --tests runner.APICucumberTest

# hanya Web UI
./gradlew test --tests runner.WebUICucumberTest
```

Di Windows gunakan `gradlew.bat` sebagai pengganti `./gradlew`.
Laporan Cucumber tersimpan di folder `reports/`.

Catatan: skenario checkout mengharapkan cart akun `Erien` hanya berisi 1 Samsung galaxy s6
(dari skenario "User can add product to cart"). Kalau cart masih berisi item dari run sebelumnya
yang gagal, total harga tidak akan 360 USD — kosongkan cart dulu lewat website.
