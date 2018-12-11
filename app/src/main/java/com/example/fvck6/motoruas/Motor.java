package com.example.fvck6.motoruas;

public class Motor
{
    private String nama;
    private String merk;
    private String harga;
    private int gambar;

    public static final Motor[] listmotor=
            {
                    new Motor("CBR250RR","Merk : Honda","Harga : 75 JT",R.drawable.hondo),
                    new Motor("Yamaha R25","Merk : Yamaha", "Harga : 67 JT",R.drawable.yamaha),
                    new Motor("Suzuki GSX-R150","Merk : Suzuki", "Harga : 25 JT",R.drawable.suzuki),
                    new Motor("Kawasaki Versys-X 250 Tourer", "Merk : Kawasaki", "Harga : 74 JT",R.drawable.kawasaki),
                    new Motor("Ducati 1200S", "Merk : Ducati","Harga : 490 JT",R.drawable.ducati),
            };

    private Motor(String nama,String merk,String harga,int gambar)
    {
        this.nama = nama;
        this.merk = merk;
        this.harga = harga;
        this.gambar = gambar;
    }

    public String getNama()
    {
        return nama;
    }

    public String getMerk()
    {
        return merk;
    }

    public String getHarga()
    {
        return harga;
    }

    public int getGambar()
    {
        return gambar;
    }

    @Override
    public String toString()
    {
        return this.nama;
    }
}
