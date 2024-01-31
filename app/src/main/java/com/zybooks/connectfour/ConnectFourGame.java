package com.zybooks.connectfour;

public class ConnectFourGame {
    private static int[][] board;

    static final int ROW = 7;
    static final int COL = 6;
    static final int EMPTY = 0;
    static final int BLUE = 1;
    static final int RED = 2;
    static final int DISC = 42;

    static int player = BLUE;


    public ConnectFourGame(){
        board = new int[7][6];
    }

    public void newGame() {
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 6; col++) {
                board[row][col] = EMPTY;
            }
        }
        player = BLUE;
    }
    public static int getDisc(int row, int col){
        return board[row][col];
    }

    public static boolean isGameOver(){
        if(isBoardFull() || isRedWin() || isBlueWin()){
            return true;
        }
        return false;
    }
    public static boolean isBlueWin(){
        if(checkHorizontalBLUE() || checkVerticalBLUE() || checkDiagonalBLUE()){
            return true;
        }
        return false;
    }
    public static boolean isRedWin(){
        if(checkHorizontalRED() || checkVerticalRED() || checkDiagonalRED()){
            return true;
        }
        return false;
    }
    public static boolean checkHorizontalRED(){
        for(int i=0; i< ROW; i++){
            int l=0, r=1;
            while(r<COL){
                //System.out.println(l+" "+r);

                //System.out.println("1:"+board[i][r-1] + "   position: "+i+" "+(r-1));
                //System.out.println("2:"+board[i][r] + "   position: "+i+" "+(r));
                if(board[i][r-1] != board[i][r] || board[i][r] == EMPTY || board[i][r-1] == EMPTY || board[i][r] == BLUE || board[i][r-1] == BLUE) {
                    l = r;
                }
                else {
                    if(r-l >= 3){
                        return true;
                    }
                }
                r++;

            }
        }
        return false;
    }

    public static boolean checkVerticalRED() {
        for(int i=0; i< COL; i++){
            int l=0, r=1;
            while(r<ROW){
                //System.out.println(l+" "+r);

                //System.out.println("1:"+board[i][r-1] + "   position: "+i+" "+(r-1));
                //System.out.println("2:"+board[i][r] + "   position: "+i+" "+(r));
                if(board[r-1][i] != board[r][i] || board[r][i] == EMPTY || board[r-1][i] == EMPTY || board[r][i] == BLUE || board[r-1][i] == BLUE) {
                    l = r;
                }
                else {
                    if(r-l >= 3){
                        return true;
                    }
                }
                r++;

            }
        }
        return false;
    }

    public static boolean checkDiagonalRED() {
        return checkTopLeftBottomRightRED() || checkTopRightBottomLeftRED();
    }

    public static boolean checkTopLeftBottomRightRED() {
        for(int i=3; i< ROW; i++){
            int l_x=0, r_x=1;
            int r_y=i-1;
            while(r_y>=0 && r_x<COL){
                //System.out.println(l_x+" "+r_x);

                //System.out.println("1:"+board[i][r-1] + "   position: "+i+" "+(r-1));
                //System.out.println("2:"+board[i][r] + "   position: "+i+" "+(r));
                if(board[r_y+1][r_x-1] != board[r_y][r_x] || board[r_y][r_x] == EMPTY || board[r_y+1][r_x-1] == EMPTY || board[r_y][r_x] == BLUE || board[r_y+1][r_x-1] == BLUE) {
                    l_x = r_x;
                }
                else {
                    if(r_x-l_x >= 3){
                        return true;
                    }
                }
                r_x++;
                r_y--;

            }
        }
        for(int i=0; i< COL-3; i++){
            int l_x=i, r_x=i+1;
            int r_y=ROW-2;
            while(r_y>=0 && r_x<COL){
                //System.out.println(l_x+" "+r_x);

                //System.out.println("1:"+board[i][r-1] + "   position: "+i+" "+(r-1));
                //System.out.println("2:"+board[i][r] + "   position: "+i+" "+(r));
                if(board[r_y+1][r_x-1] != board[r_y][r_x] || board[r_y][r_x] == EMPTY || board[r_y+1][r_x-1] == EMPTY || board[r_y][r_x] == BLUE || board[r_y+1][r_x-1] == BLUE) {
                    l_x = r_x;
                }
                else {
                    if(r_x-l_x >= 3){
                        return true;
                    }
                }
                r_x++;
                r_y--;

            }
        }
        return false;
    }
    public static boolean checkTopRightBottomLeftRED() {
        for(int i=1; i< ROW-2; i++){
            int l_x=0, r_x=1;
            int r_y=i+1;
            while(r_y<ROW && r_x<COL){
                //System.out.println(l_x+" "+r_x);

                //System.out.println("1:"+board[i][r-1] + "   position: "+i+" "+(r-1));
                //System.out.println("2:"+board[i][r] + "   position: "+i+" "+(r));
                if(board[r_y-1][r_x-1] != board[r_y][r_x] || board[r_y][r_x] == EMPTY || board[r_y-1][r_x-1] == EMPTY || board[r_y][r_x] == BLUE || board[r_y-1][r_x-1] == BLUE) {
                    l_x = r_x;
                }
                else {
                    if(r_x-l_x >= 3){
                        return true;
                    }
                }
                r_x++;
                r_y++;

            }
        }
		/*
		{ 0, 0, 0, 1, 0, 1 },
		{ 0, 0, 1, 0, 1, 0 },
		{ 0, 1, 0, 1, 0, 1 },
		{ 1, 0, 1, 0, 1, 0 },
		{ 0, 1, 0, 1, 0, 1 },
		{ 1, 0, 1, 0, 1, 1 },
		{ 0, 1, 0, 1, 1, 1 },
	*/
        for(int i=0; i< COL-3; i++){
            int l_x=i, r_x=i+1;
            int r_y=1;
            while(r_y<ROW && r_x<COL){
                //System.out.println(l_x+" "+r_x);

                //System.out.println("1:"+board[i][r-1] + "   position: "+i+" "+(r-1));
                //System.out.println("2:"+board[i][r] + "   position: "+i+" "+(r));
                if(board[r_y-1][r_x-1] != board[r_y][r_x] || board[r_y][r_x] == EMPTY || board[r_y-1][r_x-1] == EMPTY || board[r_y][r_x] == BLUE || board[r_y-1][r_x-1] == BLUE) {
                    l_x = r_x;
                }
                else {
                    if(r_x-l_x >= 3){
                        return true;
                    }
                }
                r_x++;
                r_y++;

            }
        }
        return false;
    }

    public static boolean checkHorizontalBLUE(){
        for(int i=0; i< ROW; i++){
            int l=0, r=1;
            while(r<COL){
                //System.out.println(l+" "+r);

                //System.out.println("1:"+board[i][r-1] + "   position: "+i+" "+(r-1));
                //System.out.println("2:"+board[i][r] + "   position: "+i+" "+(r));
                if(board[i][r-1] != board[i][r] || board[i][r] == EMPTY || board[i][r-1] == EMPTY || board[i][r] == RED || board[i][r-1] == RED) {
                    l = r;
                }
                else {
                    if(r-l >= 3){
                        return true;
                    }
                }
                r++;

            }
        }
        return false;
    }

    public static boolean checkVerticalBLUE() {
        for(int i=0; i< COL; i++){
            int l=0, r=1;
            while(r<ROW){
                //System.out.println(l+" "+r);

                //System.out.println("1:"+board[i][r-1] + "   position: "+i+" "+(r-1));
                //System.out.println("2:"+board[i][r] + "   position: "+i+" "+(r));
                if(board[r-1][i] != board[r][i] || board[r][i] == EMPTY || board[r-1][i] == EMPTY || board[r][i] == RED || board[r-1][i] == RED) {
                    l = r;
                }
                else {
                    if(r-l >= 3){
                        return true;
                    }
                }
                r++;

            }
        }
        return false;
    }

    public static boolean checkDiagonalBLUE() {
        return checkTopLeftBottomRightBLUE() || checkTopRightBottomLeftBLUE();
    }

    public static boolean checkTopLeftBottomRightBLUE() {
        for(int i=3; i< ROW; i++){
            int l_x=0, r_x=1;
            int r_y=i-1;
            while(r_y>=0 && r_x<COL){
                //System.out.println(l_x+" "+r_x);

                //System.out.println("1:"+board[i][r-1] + "   position: "+i+" "+(r-1));
                //System.out.println("2:"+board[i][r] + "   position: "+i+" "+(r));
                if(board[r_y+1][r_x-1] != board[r_y][r_x] || board[r_y][r_x] == EMPTY || board[r_y+1][r_x-1] == EMPTY || board[r_y][r_x] == RED || board[r_y+1][r_x-1] == RED) {
                    l_x = r_x;
                }
                else {
                    if(r_x-l_x >= 3){
                        return true;
                    }
                }
                r_x++;
                r_y--;

            }
        }
        for(int i=0; i< COL-3; i++){
            int l_x=i, r_x=i+1;
            int r_y=ROW-2;
            while(r_y>=0 && r_x<COL){
                //System.out.println(l_x+" "+r_x);

                //System.out.println("1:"+board[i][r-1] + "   position: "+i+" "+(r-1));
                //System.out.println("2:"+board[i][r] + "   position: "+i+" "+(r));
                if(board[r_y+1][r_x-1] != board[r_y][r_x] || board[r_y][r_x] == EMPTY || board[r_y+1][r_x-1] == EMPTY || board[r_y][r_x] == RED || board[r_y+1][r_x-1] == RED) {
                    l_x = r_x;
                }
                else {
                    if(r_x-l_x >= 3){
                        return true;
                    }
                }
                r_x++;
                r_y--;

            }
        }
        return false;
    }
    public static boolean checkTopRightBottomLeftBLUE() {
        for(int i=1; i< ROW-2; i++){
            int l_x=0, r_x=1;
            int r_y=i+1;
            while(r_y<ROW && r_x<COL){
                //System.out.println(l_x+" "+r_x);

                //System.out.println("1:"+board[i][r-1] + "   position: "+i+" "+(r-1));
                //System.out.println("2:"+board[i][r] + "   position: "+i+" "+(r));
                if(board[r_y-1][r_x-1] != board[r_y][r_x] || board[r_y][r_x] == EMPTY || board[r_y-1][r_x-1] == EMPTY || board[r_y][r_x] == RED || board[r_y-1][r_x-1] == RED) {
                    l_x = r_x;
                }
                else {
                    if(r_x-l_x >= 3){
                        return true;
                    }
                }
                r_x++;
                r_y++;

            }
        }
		/*
		{ 0, 0, 0, 1, 0, 1 },
		{ 0, 0, 1, 0, 1, 0 },
		{ 0, 1, 0, 1, 0, 1 },
		{ 1, 0, 1, 0, 1, 0 },
		{ 0, 1, 0, 1, 0, 1 },
		{ 1, 0, 1, 0, 1, 1 },
		{ 0, 1, 0, 1, 1, 1 },
	*/
        for(int i=0; i< COL-3; i++){
            int l_x=i, r_x=i+1;
            int r_y=1;
            while(r_y<ROW && r_x<COL){
                //System.out.println(l_x+" "+r_x);

                //System.out.println("1:"+board[i][r-1] + "   position: "+i+" "+(r-1));
                //System.out.println("2:"+board[i][r] + "   position: "+i+" "+(r));
                if(board[r_y-1][r_x-1] != board[r_y][r_x] || board[r_y][r_x] == EMPTY || board[r_y-1][r_x-1] == EMPTY || board[r_y][r_x] == RED || board[r_y-1][r_x-1] == RED) {
                    l_x = r_x;
                }
                else {
                    if(r_x-l_x >= 3){
                        return true;
                    }
                }
                r_x++;
                r_y++;

            }
        }
        return false;
    }

    public static boolean isBoardFull(){
        for(int i=0; i< ROW; i++){
            for(int j=0; j<COL; j++){
                if(board[i][j] == EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    public void setState(String gameState){
        int index =0;
        for(int i=0; i< ROW; i++){
            for(int j=0; j< COL; j++){
                board[i][j] = gameState.charAt(index);
                index++;
            }
        }
    }
    public void selectDisc(int row, int col){
        for(int i=ROW-1; i>=0; i--){
            if(board[i][col] == EMPTY){

                //Enter the color
                board[i][col] = player;
                //Update the player
                if(player == BLUE){
                    player = RED;
                }
                else{
                    player = BLUE;
                }
                break;
            }
        }
    }
    public static String getState(){
        StringBuilder sbf = new StringBuilder();
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 6; col++) {
                sbf.append(board[row][col]);
            }
        }
        return sbf.toString();
    }
}
