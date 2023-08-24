package challange1.diffaazkhani;

import java.io.*;
import java.util.Scanner;

/*
Nama  : Diffa Azkhani
Kelas : BEJ-1
Email : diffaazkhani1@gmail.com / 112202006496@mhs.dinus.ac.id
 */

public class MenuKasirPesanMakananChallenge1 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] makananArray = {"Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Gede", "Es Jeruk"};
    private static final int[] hargaArray = {15000, 13000, 18000, 3000, 5000};
    private static final int[][] pesananArray = new int[5][];
    private static final String namaFileNota = "nota.txt";

    public static void main(String[] args) {
        do {
            tampilanMenuUtama();
            String pilihan = inputPengguna("Pilih [1..2]");
            switch (pilihan) {
                case "1":
                    pemesananMakanan();
                    break;
                case "2":
                    detailPemesananDanPembayaran();
                    break;
                case "0":
                    System.out.println("Terima kasih sudah menggunakan aplikasi kami");
                    scanner.close();
                    return;
                default:
                    System.out.println("Mohon masukkan pilihan yang ada di menu");
            }
        } while (true);
    }

    // hanya method untuk tampilan menu utama saja
    public static void tampilanMenuUtama() {
        System.out.println("==========================");
        System.out.println("Program Pemesanan Makanan ");
        System.out.println("By Diffa Azkhani          ");
        System.out.println("==========================");
        System.out.println("1. Pilih Menu Makanan     ");
        System.out.println("2. Bayar                  ");
        System.out.println("0. Keluar                 ");
    }

    // hanya method untuk tampilan setiap menu makanan atau minuman yang tesedia
    public static void tampilanMenuMakanan(){
        System.out.println("===================================");
        System.out.println("Selamat Datang di BinarFud         ");
        System.out.println("===================================");
        System.out.println("Silahkan pilih makanan :           ");
        System.out.println("1.  Nasi Goreng  |   15000         ");
        System.out.println("2.  Mie Goreng   |   13000         ");
        System.out.println("3.  Nasi + Ayam  |   18000         ");
        System.out.println("4.  Es Teh Gede  |   3000          ");
        System.out.println("5.  Es Jeruk     |   5000        \n");
        System.out.println("0.  Selesai dan Kembali ke Menu Utama");
    }

    // method untuk mengambil data inputan pesanan dari user yang akan di simpan di pesananArray[5][]
    public static void pemesananMakanan() {
        do{
            tampilanMenuMakanan();
            // meminta inputan angka dari user untuk memilih menu makanan atau minuman
            int pilihan = Integer.parseInt(inputPengguna("Pilih [1..5]"));

            if (pilihan >= 1 && pilihan <= 5) {
                System.out.println("==========================");
                System.out.println("Berapa Pesanan Anda       ");
                System.out.println("==========================");

                // menampilkan makanan yang di pilih ke terminal beserta harganya
                System.out.println(makananArray[pilihan - 1] + " | " + hargaArray[pilihan - 1]);
                int jumlah = Integer.parseInt(inputPengguna("Jumlah pesanan"));

                // megecek apakah array kedua sudah terinisialisasi jika belum maka akan dibuatkan
                // menjadi array 2 dimensi, array 2 dimensi ini nanti untuk menampung jumlah pesanan
                // dari masing masing makanan yang di pilih
                if (pesananArray[pilihan - 1] == null) {
                    pesananArray[pilihan - 1] = new int[makananArray.length];
                }
                pesananArray[pilihan - 1][pilihan - 1] += jumlah;
                System.out.println("Pesanan berhasil ditambahkan ke keranjang.");
            }else if(pilihan == 0){
                return;
            }
        }while(true);
    }

    public static void detailPemesananDanPembayaran() {
        System.out.println("===================================");
        System.out.println("Detail Pesanan dan Pembayaran      ");
        System.out.println("===================================");
        int totalHarga = 0;
        int totalPesanan = 0;

        // menampilkan keseluruhan data pesanan yang tersimpan di dalam array
        for (int i = 0; i < makananArray.length; i++) {
            for (int j = 0; pesananArray[i] != null && j < makananArray.length; j++) {
                // memastikan bahwa hanya pesanan dengan jumlah pesanan lebih dari 0 yang akan di tampilkan nanti
                if (pesananArray[i][j] > 0) {
                    System.out.println(makananArray[j] + "    \t\t" + pesananArray[i][j] + " \t\t" + hargaArray[j]);
                    totalHarga += hargaArray[j] * pesananArray[i][j];
                    totalPesanan += pesananArray[i][j];
                }
            }
        }
        System.out.println("===================================");
        System.out.println("Total Pembayaran\t"+ totalPesanan + "\t\t"+ totalHarga);

        System.out.println("1. Konfirmasi dan Bayar ");
        System.out.println("2. Kembali ke menu utama");

        // mengambil input user untuk melakukan konfirmasi atau kembali ke menu utama
        int pilihan = Integer.parseInt(inputPengguna("=>"));
        switch (pilihan){
            case 1:
                cetakNota();
                break;
            case 2:
                tampilanMenuUtama();
                break;
            default:
                System.out.println("Mohon masukkan pilihan yang ada di menu");

        }
    }

    // method untuk mengambil input pengguna dengan memberikan keterangan inputan saat method di panggil
    public static String inputPengguna(String keterangan) {
        System.out.print(keterangan + " : ");
        return scanner.nextLine();
    }

    // method untuk menyimpan/menulis data ke objek writer untuk nantinya dapat di cetak
    public static void simpanNota() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(namaFileNota))) {
            writer.println("===================================");
            writer.println("BinarFud                           ");
            writer.println("===================================\n");
            writer.println("Terima kasih sudah memesan         ");
            writer.println("di BinarFud                        \n");
            writer.println("Dibawah ini adalah pesanan anda    \n");
            int totalHarga = 0;
            int totalPesanan = 0;
            for (int i = 0; i < makananArray.length; i++) {
                for (int j = 0; pesananArray[i] != null && j < makananArray.length; j++) {
                    if (pesananArray[i][j] > 0) {
                        writer.println(makananArray[j] + "    \t\t" + pesananArray[i][j] + " \t\t" + hargaArray[j]);
                        totalHarga += hargaArray[j] * pesananArray[i][j];
                        totalPesanan += pesananArray[i][j];
                    }
                }
            }
            writer.println("===================================");
            writer.println("Total \t\t\t\t"+ totalPesanan + "\t\t"+ totalHarga + "\n");
            writer.println("Pembayaran : BinarCash\n           ");
            writer.println("===================================");
            writer.println("Simpan struk ini sebagai bukti     ");
            writer.println("pembayaran                         ");
            writer.println("===================================");

            System.out.println("Nota berhasil disimpan di file: " + namaFileNota);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan nota: " + e.getMessage());
        }
    }

    // method untuk melakukan cetak nota yang dimana sebelumnya harus sudah di tuliskan terlebih dahulu
    public static void cetakNota() {
        simpanNota();
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFileNota))) {
            String line;

            // membaca baris file yang sudah dituliskan tadi pada method simpanNota
            // hasil file yang sudah dibaca akan disimpan di lokasi projek
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca nota: " + e.getMessage());
        }
    }
}