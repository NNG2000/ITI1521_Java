import java.util.LinkedList;

public class ListOfGamesGenerator {
	

   /**
	* generates all different games for the specified
	* parameters. Each game is recorded only once. 
	* once a game is finished, it is not extended further
	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
    * @param sizeWin
    *  the number of cells that must be aligned to win.
    * @return
    * a list of lists of game instances, ordered by levels
  	*/
	public static LinkedList<LinkedList<TicTacToeGame>> generateAllGames(int lines, int columns, int winLength){

		//NB retoune la grande liste
		LinkedList<LinkedList<TicTacToeGame>>bigList = new LinkedList<LinkedList<TicTacToeGame>>();
		
		
		
        //creons notre jeu de base
		int choise = lines*columns;
		int compt = 0;
		while(compt<lines*columns){
			
			
			//1. faisons une liste pour prendre des positions au hasard
			int[] listChoise = new int[lines*columns];
		    int i=0;
            int entierBoard = 0;
            TicTacToeGame game = new TicTacToeGame (lines, columns, winLength);			
		    while(i<=listChoise.length){
			    if (i!= choise){
				    listChoise[i]=entierBoard;
					entierBoard++;
					i++;
				} 
				else if(i == listChoise.length){
					choise = Utils.generator.nextInt(listChoise.length);
					while (game.valueAt(choise)==CellValue.EMPTY){
					    choise = Utils.generator.nextInt(listChoise.length);
					}
					i++;
				}
				else{
					entierBoard++;
					i++;	
				}	
		    }
			
			
			// creons nos petites listes et ajouttons le tableau vide a la premiere position
			LinkedList<TicTacToeGame>smalList = new LinkedList<TicTacToeGame>();
		    bigList.add(smalList);
			bigList.get(0).addFirst (game);
			
			
			//jouons dans notre jeu de base
			game.play(choise);
			
			int j=1;
			while (j<=game.lines*game.columns){
				if (game.valueAt(j-1)==CellValue.EMPTY){
					TicTacToeGame copyX = new TicTacToeGame(game, j);
					
					//mettons notre copyX dans la liste des listes
					
					
					if ((copyX!=null)||game.equals(copyX)==false){
					    bigList.get(compt).add(copyX);
					}	
					
					j++;
				}
				else{
					j++;
				}
			}
			
        
        compt++;
		}


        return bigList;		

	}
}