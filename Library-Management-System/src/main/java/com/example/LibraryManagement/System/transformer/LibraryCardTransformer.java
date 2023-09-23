package com.example.LibraryManagement.System.transformer;

import com.example.LibraryManagement.System.Enum.CardStatus;
import com.example.LibraryManagement.System.model.LibraryCard;

import java.util.UUID;

public class LibraryCardTransformer {

    public static LibraryCard prepareLibraryCard() {
        return LibraryCard.builder()
                .cardNo(String.valueOf(UUID.randomUUID()))
                .cardStatus(CardStatus.ACTIVE)
                .build();
    }

}
