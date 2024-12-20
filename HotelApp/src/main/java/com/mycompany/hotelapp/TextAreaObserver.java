/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelapp;

import javax.swing.JTextArea;

public class TextAreaObserver implements Observer {
    private final JTextArea textArea;

    public TextAreaObserver(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void update(String message) {
        textArea.append(message + "\n");
    }
}
