package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    public ClientThread (Socket socket) {
        this.socket = socket;
    }

    public void run () {
        PrintWriter out = null;
        BufferedReader input = null;
        BufferedReader bufferedReader = null;

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Zdravo klijentu !!!");
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                bufferedReader = new BufferedReader(new FileReader("korisnici.txt"));
                out.println("Ucitaj korisnicko ime:         (0 za izlazak)");
                String korisnik = input.readLine();
                if (korisnik.equals("0")) {
                    break;
                }
                out.println("Ucitaj lozinku:        (0 za izlazak)");
                String lozinka = input.readLine();
                if (lozinka.equals("0")) {
                    break;
                }
                String linija = null;
                boolean postoji = false;
                while ((linija = bufferedReader.readLine()) != null) {
                    String[] str = linija.split("\\|");
                    if (str[0].equals(korisnik) && str[1].equals(lozinka)) {
                        postoji = true;
                    }
                }
                bufferedReader.close();
                if(postoji) {
                    out.println("Uspeh");
                    break;
                }else {
                    out.println("Neispravno korisnicko ime/lozinka");
                }
            }
            input.close();
            out.close();
            socket.close();
            bufferedReader.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
