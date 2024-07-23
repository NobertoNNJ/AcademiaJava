import java.util.Scanner;

public class Jogo_da_velha {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

		String[][] tabuleiro = { 
						{" "," "," "},
						{" "," "," "},
						{" "," "," "}
        };
        String jogador_atual = "X";
        int jogador = 1;
        boolean jogo_em_andamento = true;

        while(jogo_em_andamento){
            imprimir_tabuleiro(tabuleiro);

            System.out.println("Jogador " + jogador + ", escolha sua posição (linha e coluna): ");

            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();

            if(linha < 0  || linha > 2 || coluna < 0 || coluna > 2 || !tabuleiro[linha][coluna].equals(" ")){
                System.out.println("Movimento Inválido.");
            }
            else{
                tabuleiro[linha][coluna] = jogador_atual;

                if(verificar_vitoria(tabuleiro, jogador_atual)){
                    imprimir_tabuleiro(tabuleiro);
                    System.out.println("Jogador " + jogador + " venceu!");
                    jogo_em_andamento = false;
                }else if (verificar_empate(tabuleiro)) {
                    imprimir_tabuleiro(tabuleiro);
                    System.out.println("O jogo empatou!");
                    jogo_em_andamento = false;
                } else {
                    jogador_atual = jogador_atual.equals("X") ? "O" : "X";
                    jogador = jogador == 1 ? 2 : 1;
                }
            }
        }
        scanner.close();
	}
    public static void imprimir_tabuleiro(String[][] tabuleiro){
        System.out.print("\n\n 0   1    2\n\n");
			for (int posicao1 = 0; posicao1 < 3; posicao1++) {
				for (int posicao2 = 0; posicao2 < 3; posicao2++) {
					System.out.print(" " + tabuleiro[posicao1][posicao2]);
					if (posicao2 < 2) {
						System.out.print(" | ");
					}
					if (posicao2 == 2) {
						System.out.print("  " + posicao1);
					}
				}
				if (posicao1 < 2) {
					System.out.print("\n------------");
				}
				System.out.println("\n");
			}
    }

    public static boolean verificar_vitoria(String[][] tabuleiro, String jogador) {
        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0].equals(jogador) && tabuleiro[i][1].equals(jogador) && tabuleiro[i][2].equals(jogador)) ||
                (tabuleiro[0][i].equals(jogador) && tabuleiro[1][i].equals(jogador) && tabuleiro[2][i].equals(jogador))) {
                return true;
            }
        }
        if ((tabuleiro[0][0].equals(jogador) && tabuleiro[1][1].equals(jogador) && tabuleiro[2][2].equals(jogador)) ||
            (tabuleiro[0][2].equals(jogador) && tabuleiro[1][1].equals(jogador) && tabuleiro[2][0].equals(jogador))) {
            return true;
        }
        return false;
    }
    public static boolean verificar_empate(String[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
    
}