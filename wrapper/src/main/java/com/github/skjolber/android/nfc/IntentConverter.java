package com.github.skjolber.android.nfc;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntentConverter {


    public static Intent convert(Intent input) {

        Intent output = new Intent();

        output.setAction(input.getAction());

        // detect supported types
        if(input.hasExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)) {
            Parcelable[] messages = input.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            ArrayList<NdefMessage> list = new ArrayList<>();
            for (int i = 0; i < messages.length; i++) {
                list.add((NdefMessage) messages[i]);
            }

            output.putParcelableArrayListExtra(NfcAdapter.EXTRA_NDEF_MESSAGES, list);
        }

        if(input.hasExtra(NfcAdapter.EXTRA_TAG)) {
            android.nfc.Tag tag = (android.nfc.Tag) input.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            input.putExtra(NfcAdapter.EXTRA_TAG, new TagWrapper(tag));
        }

        if(input.hasExtra(NfcAdapter.EXTRA_AID)) {
            android.nfc.Tag tag = (android.nfc.Tag) input.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            input.putExtra(NfcAdapter.EXTRA_TAG, new TagWrapper(tag));
        }


        if(input.hasExtra(NfcAdapter.EXTRA_ID)) {
            byte[] id = input.getByteArrayExtra(NfcAdapter.EXTRA_ID);

            output.putExtra(NfcAdapter.EXTRA_ID, id);
        }

        // TODO forward all types

        return output;
    }

}
