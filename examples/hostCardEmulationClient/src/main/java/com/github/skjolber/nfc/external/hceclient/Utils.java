package com.github.skjolber.nfc.external.hceclient;

import android.app.Activity;
import android.content.res.Resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    private static String TAG = Utils.class.getName();

    public static byte[] getResource(int r, Activity a) throws IOException {
        Resources res = a.getResources();
        InputStream in = res.openRawResource(r);

        byte[] b = new byte[4096];

        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        int read;
        do {
            read = in.read(b, 0, b.length);
            if (read == -1) {
                break;
            }
            bout.write(b, 0, read);
        } while (true);

        return bout.toByteArray();
    }


    public static byte[] asBytes(Integer ... content) {
        byte[] cmd = new byte[content.length];
        for(int i = 0; i < content.length; i++) {
            cmd[i] = content[i].byteValue();
        }

        return cmd;

    }

    public static String toHexString(byte[] bin, int offset, int length) {
        StringBuilder sb = new StringBuilder();
        for (int x = offset; x < offset + length; x++) {
            sb.append(String.format("%02X ", bin[x]));
        }
        if(length > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString().toUpperCase();
    }

    public static String toHexString(byte[] bin) {
        return toHexString(bin, 0, bin.length);
    }

}
