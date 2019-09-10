package com.xtb4.sortfromfiles.view;

import com.xtb4.sortfromfiles.exceptions.ArgError;

public class View {

    public static void showError(ArgError type){
        System.out.println(TextDecoration.ANSI_RED + handlerException(type) + TextDecoration.ANSI_RESET);
    }

    public static void showStartMessage(){
        System.out.println(Messages.START_SORT);
    }

    public static void showResult(String output){
        System.out.println(Messages.READY);
        System.out.println(TextDecoration.ANSI_GREEN + String.format(Messages.OUTPUT_FILE_PATH, output) + TextDecoration.ANSI_RESET);
    }

    private static String handlerException(ArgError type) {
        switch (type) {
            case WRONG_ORDER: return Messages.WRONG_ORDER;
            case WRONG_TYPE: return Messages.WRONG_TYPE;
            case WRONG_OUTPUT_FILE: return Messages.WRONG_OUTPUT_FILE;
            case WRONG_INPUT_FILE: return Messages.WRONG_INPUT_FILE;
            default: return null;
        }
    }
}