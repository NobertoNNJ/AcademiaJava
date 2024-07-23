import java.util.Scanner;

public class ChessBoard {
    public static void main(String[] args) {
        String[][] board = { 
            {"R","N","B","Q","K","B","N","R"},
            {"P","P","P","P","P","P","P","P"},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {"p","p","p","p","p","p","p","p"},
            {"r","n","b","q","k","b","n","r"}
        };

        Scanner scanner = new Scanner(System.in);
        boolean whiteTurn = true; // True for White's turn, false for Black's turn

        while (true) {
            // Imprimir o tabuleiro
            imprimirBoard(board);

            // Solicitar entrada do usuário
            String player = whiteTurn ? "Branco" : "Preto";
            System.out.println("É a vez das peças " + player + ".");
            System.out.println("Digite a posição inicial (linha e coluna) e a posição final (linha e coluna) separadas por espaço (ex: 6 3 4 3) ou 'sair' para encerrar:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("sair")) {
                break;
            }

            String[] tokens = input.split(" ");
            if (tokens.length != 4) {
                System.out.println("Entrada inválida. Por favor, insira quatro coordenadas.");
                continue;
            }

            try {
                int startRow = Integer.parseInt(tokens[0]);
                int startCol = Integer.parseInt(tokens[1]);
                int endRow = Integer.parseInt(tokens[2]);
                int endCol = Integer.parseInt(tokens[3]);

                if (isValidPosition(startRow, startCol) && isValidPosition(endRow, endCol)) {
                    char piece = board[startRow][startCol].charAt(0);
                    if ((whiteTurn && piece == 'P') || (!whiteTurn && piece == 'p')) {
                        if (isValidCapture(board, startRow, startCol, endRow, endCol, piece)) {
                            capturePawn(board, startRow, startCol, endRow, endCol);
                            whiteTurn = !whiteTurn; // Alternar para o próximo jogador
                        } else if (isValidMove(board, startRow, startCol, endRow, endCol, piece)) {
                            movePawn(board, startRow, startCol, endRow, endCol);
                            whiteTurn = !whiteTurn; // Alternar para o próximo jogador
                        } else {
                            System.out.println("Movimento ou captura inválido.");
                        }
                    } else {
                        System.out.println("A posição inicial não contém um peão válido para este jogador.");
                    }
                } else {
                    System.out.println("Coordenadas fora do tabuleiro.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. As coordenadas devem ser números.");
            }
        }

        scanner.close();
    }

    // Função para imprimir o tabuleiro
    public static void imprimirBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    // Função para mover um peão
    public static void movePawn(String[][] board, int startRow, int startCol, int endRow, int endCol) {
        board[endRow][endCol] = board[startRow][startCol];
        board[startRow][startCol] = " ";
    }

    // Função para capturar uma peça com o peão
    public static void capturePawn(String[][] board, int startRow, int startCol, int endRow, int endCol) {
        board[endRow][endCol] = board[startRow][startCol];
        board[startRow][startCol] = " ";
    }

    // Valida o movimento básico do peão
    public static boolean isValidMove(String[][] board, int startRow, int startCol, int endRow, int endCol, char piece) {
        int direction = (piece == 'P') ? -1 : 1;
        boolean isInitialMove = (piece == 'P' && startRow == 6) || (piece == 'p' && startRow == 1);

        if (board[endRow][endCol].equals(" ")) {
            return (startRow - endRow == direction && startCol == endCol) ||
                   (isInitialMove && startRow - endRow == 2 * direction && startCol == endCol);
        }
        return false;
    }

    // Valida a captura com o peão
    public static boolean isValidCapture(String[][] board, int startRow, int startCol, int endRow, int endCol, char piece) {
        int direction = (piece == 'P') ? -1 : 1;
        if (!board[endRow][endCol].equals(" ") && Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 1) {
            return (startRow - endRow == direction);
        }
        return false;
    }

    // Verifica se a posição é válida dentro do tabuleiro
    public static boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
