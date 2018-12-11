package com.example.fvck6.motoruas;

public class Warna
{
    private String nama;
    private int gambar;
    private int gambar1;

    public static final Warna[] listwarna=
            {
                    new Warna("CBR250RR",R.drawable.hondo,R.drawable.hondo1),
                    new Warna("Yamaha R25",R.drawable.yamaha,R.drawable.yamaha1),
                    new Warna("Suzuki GSX-R150",R.drawable.suzuki,R.drawable.suzuki2),
                    new Warna("Kawasaki Versys-X 250 Tourer",R.drawable.kawasaki,R.drawable.kawasaki1),
                    new Warna("Ducati 1200S",R.drawable.ducati,R.drawable.ducati1),
            };

    private Warna(String nama, int gambar,int gambar1)
    {
        this.nama = nama;
        this.gambar = gambar;
        this.gambar1 = gambar1;
    }

    public String getNama()
    {
        return nama;
    }

    public int getGambar1()
    {
        return gambar1;
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
