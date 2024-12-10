import java.util.Scanner;

public class CafeBumi8 {
    static Scanner kel8 = new Scanner(System.in);
    static String[] daftarMenu = {"Roti Bakar Coklat", "Roti Bakar Keju", "Kentang Goreng", "Mie Goreng Spesial", "Nasi Goreng Mawut",
                                   "Cappucino", "Espresso", "Greentea", "Teh Tarik", "Susu Coklat"};
    static int[] hargaMenu = {12000, 12000, 10000, 15000, 15000,
                               7000, 10000, 13000, 8000, 10000};
    static String[][] pesanan = new String[100][5];
    static int jumlahPesanan = 0;

    public static void main(String[] args) {
        boolean menu = true;

        while (menu) {
            System.out.println("\n=========== CAFE BUMI 8 ===========");
            System.out.println("1. Transaksi Pesanan");
            System.out.println("2. Tampilkan Daftar Pesanan");
            System.out.println("3. Total Pendapatan Harian");
            System.out.println("4. Keluar");
            System.out.println("===================================");
            System.out.print("Masukkan Pilihan: ");
            int pilihan = kel8.nextInt();

            switch (pilihan) {
                case 1:
                    prosesPesanan();
                    break;
                case 2:
                    tampilkanPesanan();
                    break;
                case 3:
                    hitungTotalPendapatan();
                    break;
                case 4:
                    menu = false;
                    System.out.println("\nTerima kasih! Silahkan datang kembali.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    public static void prosesPesanan() {
        kel8.nextLine();
        System.out.print("Masukkan nama pelanggan: ");
        String namaPelanggan = kel8.nextLine();
        String nomorMeja;
        boolean meja = false;

        do {
            System.out.print("Masukkan nomor meja: ");
            nomorMeja = kel8.nextLine();
            meja = true;

            for (int i = 0; i < jumlahPesanan; i++) {
                if (pesanan[i][1].equals(nomorMeja)) {
                    System.out.println("Nomor meja ini sudah terpakai. Silakan pilih nomor meja lain.");
                    meja = false;
                    break;
                }
            }
        } while (!meja);

        int totalHargaPesanan = 0;

        tampilkanMenu();
        while (true) {
            System.out.print("Pilih menu (masukkan nomor menu, atau 0 untuk selesai): ");
            int pilihanMenu = kel8.nextInt();

            if (pilihanMenu == 0) break;

            if (pilihanMenu < 1 || pilihanMenu > daftarMenu.length) {
                System.out.println("Pilihan menu tidak valid. Silakan coba lagi.");
                continue;
            }

            System.out.print("Masukkan jumlah item untuk " + daftarMenu[pilihanMenu - 1] + ": ");
            int jumlahItem = kel8.nextInt();

            if (jumlahItem <= 0) {
                System.out.println("Jumlah item harus lebih dari 0. Silakan coba lagi.");
                continue;
            }

            int hargaItem = hargaMenu[pilihanMenu - 1] * jumlahItem;
            totalHargaPesanan += hargaItem;

            pesanan[jumlahPesanan][0] = namaPelanggan;
            pesanan[jumlahPesanan][1] = nomorMeja;
            pesanan[jumlahPesanan][2] = daftarMenu[pilihanMenu - 1];
            pesanan[jumlahPesanan][3] = String.valueOf(jumlahItem);
            pesanan[jumlahPesanan][4] = String.valueOf(hargaItem);
            jumlahPesanan++;
        }

        System.out.println("Pesanan berhasil ditambahkan.");
        System.out.println("-----------------------------------");
        System.out.println("Total harga pesanan: Rp " + totalHargaPesanan);
    }

    public static void tampilkanMenu() {
        System.out.println("\n============ MENU KAFE ============");
        for (int i = 0; i < daftarMenu.length; i++) {
            System.out.println((i + 1) + ". " + daftarMenu[i] + " - Rp " + hargaMenu[i]);
        }
        System.out.println("===================================");
    }

    public static void tampilkanPesanan() {
        System.out.println("\n========== DAFTAR PESANAN ==========");
        for (int i = 0; i < jumlahPesanan; i++) {
            boolean sudahDitampilkan = false;

            for (int j = 0; j < i; j++) {
                if (pesanan[i][0].equals(pesanan[j][0]) && pesanan[i][1].equals(pesanan[j][1])) {
                    sudahDitampilkan = true;
                    break;
                }
            }

            if (!sudahDitampilkan) {
                System.out.println("Nama Pelanggan: " + pesanan[i][0]);
                System.out.println("Nomor Meja: " + pesanan[i][1]);
                System.out.println("\nDetail Pesanan:");

                int totalHargaPelanggan = 0;
                for (int j = i; j < jumlahPesanan; j++) {
                    if (pesanan[i][0].equals(pesanan[j][0]) && pesanan[i][1].equals(pesanan[j][1])) {
                        System.out.println("- " + pesanan[j][2] + " x " + pesanan[j][3] + " = Rp " + pesanan[j][4]);
                        totalHargaPelanggan += Integer.parseInt(pesanan[j][4]);
                    }
                }

                System.out.println("\nTotal Harga Pesanan: Rp " + totalHargaPelanggan);
                System.out.println("-----------------------------------");
            }
        }
    }

    public static void hitungTotalPendapatan() {
        System.out.println("\n========== RINCIAN PENDAPATAN HARIAN ==========");
        int totalPendapatan = 0;

        for (int i = 0; i < jumlahPesanan; i++) {
            System.out.println("Pelanggan: " + pesanan[i][0]);
            System.out.println("Nomor Meja: " + pesanan[i][1]);
            System.out.println("Pesanan: " + pesanan[i][2] + " x " + pesanan[i][3] + " = Rp " + pesanan[i][4]);
            totalPendapatan += Integer.parseInt(pesanan[i][4]);
            System.out.println("----------------------------------------------");
        }
        System.out.println("Total Pendapatan Harian: Rp " + totalPendapatan);
        System.out.println("----------------------------------------------");
    }
}