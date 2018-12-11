package com.example.fvck6.motoruas;

public class Profil
{
    private String nama;
    private String durasi;
    private String videoRawId;

    public static final Profil[] profil =
            {
                    new Profil("CBR250RR","1:01","honda"),
                    new Profil("Yamaha R25", "1:31","yamaha"),
                    new Profil("Suzuki GSX-R150", "1:56","suzuki"),
                    new Profil("Kawasaki Versys-X 250 Tourer", "1:30","kawasaki"),
                    new Profil("Ducati 1200S","1:15","ducati")
            };

    private Profil(String nama,String durasi,String videoRawId)
    {
        this.nama = nama;
        this.durasi = durasi;
        this.videoRawId = videoRawId;

    }

    public String getNama()
    {
        return nama;
    }

    public String getDurasi()
    {
        return durasi;
    }

    public String getVideoRawId()
    {
        return videoRawId;
    }

    public String toString()
    {
        return this.nama;
    }
}
