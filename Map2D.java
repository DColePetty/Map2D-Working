// COLE PETTY SOLO PROJECT MONSTER DUNGEON CONSOLE GAME
import java.util.Scanner;
//STEPS TO TAKE TO CONTINUE BUILDING PROGRAM

////This menu willdisply the stats for player HP, coins, coinsHOLDING

// make the text dialog for when you use a potion and when a monster is slain appear
// Create a coinsHOLDING() that would return the coins the character is holding after a monster is slain
// Add the int value for coinsHOLDING() to AddCoins() instead of it's current RNG


public class Map2D
{
  //// STATIC (USED BY MULTIPLE METHOD) VARIABLES -------------------
  private static char[][] map = new char[][] {
    {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',},
    {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',},
    {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',},
    {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',},
    {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',},
    {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',},
    {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',},
    {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',},
    {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',},
    {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',},
  };
  private static int characterRow; //CROW
  private static int characterCol; //CCOL
  public static int rowDefault =0; //zeroes
  public static int columnDefault =0; //zeroes
  private static int monsterSpawnCount = 5; //Intended Monster Spawn Count
  public static int currentLivingMonsters = (0 + monsterSpawnCount);
  private static char characterLocation[][] = new char[characterRow][characterCol]; // CharacterLocation
  private static int characterHealthPoints = 100;  //CharacterHP
  private static int coins =0; //Coins
  private static int level= 1; //Level
  private static int currentPotions = 1; //returns current potions
  private static boolean foundPotion = false;
  private static boolean killedMonster = false;
  private static Scanner move = new Scanner(System.in); // Scanner
  
  //// MAP CREATION METHODS -----------------------------
  public static void PrintStats(int n)
  {
    //use n to control what row what is printed on
    if(n==0)System.out.print("     " + "*");
    if(n==1)System.out.print("     " + "*");
    if(n==2)System.out.print("     " + "*");
    if(n==3)System.out.print("     " + "***************************************");
    if(n==4 && foundPotion && map[0][1] != 'C' && map[1][0] != 'C')System.out.print("     " + "*    " + "You Found the Potion!(+50 HP)*****");
    if(n==4 && !foundPotion)System.out.print("     " + "*    " + "                             *****");
    if(n==5 && killedMonster)System.out.print("     " + "*    " + "You Defeated a Monster!      *****");
    if(n==5 && !killedMonster)System.out.print("     " + "*    " + "                             *****");
    if(n==6)System.out.print("     " + "*    " + "Use WASD + enter to Move" + "     *****");
    if(n==7 && characterHealthPoints>99)System.out.print("     " + "*    " + "Current hp: " + characterHealthPoints + "              *****");
    if(n==7 && characterHealthPoints<100 && characterHealthPoints >9)System.out.print("     " + "*    " + "Current hp: " + characterHealthPoints + "               *****");
    if(n==7 && characterHealthPoints <10)System.out.print("     " + "*    " + "Current hp: " + characterHealthPoints + "              *****");
    if(n==8 && coins < 10)System.out.print("     " + "*    " + "Current Coins: " + coins + "             *****");
    if(n==8 && coins >9)System.out.print("     " + "*    " + "Current Coins: " + coins + "            *****");
    if(n==9)System.out.print("     " + "***************************************");
  }
  public static void DeleteExtraC()
  {
    for(int row=9; row > 0; row--)
    {
      for(int col=9; col > 0; col--)
      {
        if(map[row][col] == 'C') {map[row][col] = ' '; return;}
        //if(map[row][0] == 'C') {map[row][0] = ' '; return;}
        //if(map[0][col] == 'C') {map[row][0] = ' '; return;}
      }
    }
  }
   
  public static void AddCoins()
  {
    //for(int i =0; i < monsterSpawnCount; i++)
    coins += ((randInt(6))) +5; //0-5 coins per monster slain
  }
  public static void LevelUp()
  {
    System.out.println("");
    System.out.println("**************************");
    System.out.println("*        Level " + level +"         *");
    System.out.println("**************************");
  //System.out.println("");
  }
  public static void Level()
  {
    System.out.println("**************************");
    System.out.println("*        Level " + level +"         *");
    System.out.println("**************************");
  //System.out.println("");
  }
  public static void PrintMap()
  {
    for(int r=0; r<map.length; r++)
    {
      for(int c=0; c<map[0].length; c++)
      {
      System.out.print(map[r][c]);
      System.out.print(" ");
      }
     PrintStats(r); 
    System.out.println("");
    }
  }
  ///// CHARACTER START METHOD---------------------------
  public static void MapCharacter()
  {
    characterRow = 0; characterCol =0;
    map[characterRow][characterCol] = 'C';
  }
  /// WALL GENERATOR METHODS ---------------------------
  public static void CreateWalls()
  {
    int wallAtColumn4=4; 
    int n = 0;
    int four = 4;
    int eight = 8;
    for(int i=0; i<10; i++)
    {
       map[n][four] = '|';
       map[n][eight] = '|';
       n+=1;
    }
  }
  public static void DeleteWalls()
  {
    for(int i =0; i < 4; i++)
    {  
      map[randInt()][4] = ' ';
      map[randInt(6)+3][8] = ' ';
    }
  }
  /// POTION METHODS --------------------------------------------
  public static void MapPotion()
  {
    int[] possiblitiesRow = {2,3,5,6,7,9};
    int tempRow = possiblitiesRow[(randInt(6))];
    //int tempRow=  randInt(9) +1;
    int tempCol = randInt(9) +1;
    if(map[tempRow][tempCol] != '|' && map[tempRow][tempCol] != 'C' && map[tempRow][tempCol] != 'M')
    {map[tempRow][tempCol] = 'P'; }
  }
  
  public static int PotionCount()
  {
    int potionCount =0;
    for(int row=0; row < 10; row++)
    {
      for(int col=0; col < 10; col++)
      {
        if(map[row][col] == 'P') potionCount = 1;
    }
  }
    return potionCount;
  }
  
  public static void PotionUse()
  {
    if(PotionCount() < currentPotions)
    {
      {currentPotions--; 
        characterHealthPoints+= 50;
        //System.out.print("You found a potion!");
        foundPotion = true;
        return; }
    }
    else foundPotion = false;
  }
  
  /// MONSTER METHODS -------------------------------------------
  public static void MapMonster()
  {
    int tempRow = randInt(9) +1;
    int tempCol = randInt(9) +1;
    if(map[tempRow][tempCol] != '|' && map[tempRow][tempCol] != 'C')
      {map[tempRow][tempCol] = 'M'; }
  }
  public static void MapMonsters(int monsterSpawnCount)
  {
    for(int numMonsters =0; CheckForMonsters() < monsterSpawnCount; numMonsters++)// the constraint here 
    {                                                                 //is the number of monsters
      MapMonster();                                 // to be generated aka "int monsterSpawnCount"
    }
  }
  public static void MonsterMove()
  {
   int temp = randInt(5);
    for(int row=0; row < 10; row++)
    {
      for(int col=0; col < 10; col++)
      { 
        if(map[row][col] == 'M')
        {
          if(temp == 1 && col>0 && map[row][col-1] != '|' && map[row][col-1] != 'C' && map[row][col-1] != 'M' && map[row][col-1] != 'P')   //left
          {map[row][col-1] = 'M'; map[row][col] = ' ';
          //System.out.println("A Monster moved left"); 
          temp = randInt(5); break; }
          
          if(temp == 2 && col<9 && map[row][col+1] != '|' && map[row][col+1] != 'C' && map[row][col+1] != 'M' && map[row][col+1] != 'P')   //right
          {map[row][col+1] = 'M'; map[row][col] = ' ';
          //System.out.println("A Monster moved right"); 
          temp = randInt(5); break; }
          
          if(temp == 3 && row>0 && map[row-1][col] != '|' && map[row-1][col] != 'C' && map[row-1][col] != 'M' && map[row-1][col] != 'P')   //up
          {map[row-1][col] = 'M'; map[row][col] = ' ';
          //System.out.println("A Monster moved up"); 
          temp = randInt(5); break; }
          
          if(temp == 4 && row<9 && map[row+1][col] != '|' && map[row+1][col] != 'C' && map[row+1][col] != 'M' && map[row+1][col] != 'P')   //down
          {map[row+1][col] = 'M'; map[row][col] = ' ';
          //System.out.println("A Monster moved down"); 
          temp = randInt(5); break; } // temp+= randInt(); temp+=10; break;
        }
    }
   }
  }
  public static int CheckForMonsters()
  {
    int monsterCount =0;
    for(int row=0; row < 10; row++)
    {
      for(int col=0; col < 10; col++)
      {
        if(map[row][col] =='M')
        {
          monsterCount++;
        }
      }
    }
    return monsterCount;
  }
  
  public static boolean MonsterKill()
  {
   // int currentLivingMonsters = CheckForMonsters();
    if(CheckForMonsters() < currentLivingMonsters)
    {currentLivingMonsters--; killedMonster = true; return true;}
    else {killedMonster = false; return false;}
  }
  
  public static void MonsterHit()
  {
    characterHealthPoints -= (randInt(15)+5);
  }

  /// RANDOM NUMBER GENERATOR METHODS -------------------
  public static int randInt()
  {
    return (int)(Math.random() *10);
  }
  
  public static int randInt(int n)
  {
   return (int)(Math.random() * n); 
  }
  ///CHARACTER MOVE METHODS ------------------------------
 /*    public static void MoveCharacter()
   {
     Scanner input = new Scanner(System.in);
     //System.out.println("1- left, 2- right, 3- down, 4- up");
     int direction = input.nextInt();
     if(direction ==1)
     CharacterLeft();
     else if(direction ==2)
     CharacterRight();
     else if(direction ==3)
     CharacterDown();
     else if(direction ==4)
     CharacterUp();
     else if(direction >4 || direction <1)
     System.out.println("Please enter a different direction");
     }
    */
  public static void MoveCharacter()
  {
    Scanner input = new Scanner(System.in);
    String direction = input.next();
    char direction1= direction.charAt(0);
     if(direction1 == 1 || direction1 == 'a')
     CharacterLeft();
     else if(direction1==2 || direction1=='d')
     CharacterRight();
     else if(direction1==3 || direction1=='s')
     CharacterDown();
     else if(direction1==4 || direction1=='w')
     CharacterUp();
     else if(direction1 != '1' || direction1 != '2' || direction1 != '3' || direction1 != '4' || direction1 != 'w' || direction1 != 'a' || direction1 != 's' || direction1 != 'd')
     System.out.println("Please enter a different direction");
  }

  private static void CharacterLeft()
  {
    if(characterCol >0 && map[characterRow][characterCol-1] != '|') characterCol--;
    map[characterRow][characterCol] = 'C'; map[characterRow][characterCol+1] = ' ';
  }
    private static void CharacterRight()
  {
    if(characterCol <9 && map[characterRow][characterCol+1] != '|') characterCol++;
    map[characterRow][characterCol] = 'C'; map[characterRow][characterCol-1] = ' ';
  }
        private static void CharacterDown()
  {
    if(characterRow <9 && map[characterRow+1][characterCol] != '|') characterRow++;
    map[characterRow][characterCol] = 'C'; map[characterRow-1][characterCol] = ' '; 
  }
            private static void CharacterUp()
  {
    if(characterRow >0 && map[characterRow-1][characterCol] != '|') characterRow--;
    map[characterRow][characterCol] = 'C'; map[characterRow+1][characterCol] = ' '; 
  }
            
  // Game Initialization method
  public static void PLAY()
  {
    MapCharacter();
    CreateWalls();
    DeleteWalls();
    MapMonsters(monsterSpawnCount);
    MapPotion();
    LevelUp();
    //PrintMap();
  }

  ///MAIN METHOD -----------------------------------------
  public static void main(String[] args)
  {
    //*Game initialization
    //*MapCharacter();
    //*CreateWalls();
    //*DeleteWalls();
    //*MapMonsters(monsterSpawnCount);
    //*MapPotion();
    //*LevelUp();
    PLAY();
    
    PrintMap(); // prints everything generated so far, must be last in Game Inititialization
    do{     //Loop to contain while player is alive
    MoveCharacter();
    MonsterMove();
    Level();
    if(MonsterKill()){MonsterHit(); AddCoins();}
    PotionUse();
    if(map[1][0] == 'C' || map[0][1] == 'C') DeleteExtraC();
    PrintMap();
    
    CheckForMonsters();
    if(CheckForMonsters()<1) //LEVELING UP 
    {
      level++; 
      characterHealthPoints+=5; 
      currentLivingMonsters = (0 + monsterSpawnCount);
      currentPotions++;
      AddCoins(); 
      PLAY();  
      DeleteExtraC();
      PrintMap();}
  }while(characterHealthPoints > 0);
  System.out.println("******************************");
  System.out.println("          You Died!           ");
  System.out.println("*        Game over!           ");
  System.out.println("*    Thanks for Playing!      ");
  System.out.println("******************************");
  // Possibility to add in stats like totalMovesMade, totalMonstersKilled, DamageTaken, etc...
  }
}