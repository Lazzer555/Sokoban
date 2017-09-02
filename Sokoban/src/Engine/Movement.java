package Engine;

/**
 *This class contains the movement for the game
 * @author Chris
 */
public class Movement {
    
    /**
     *This processes the movement of the player
     * @param level a 2d array of chars
     * @param modifier the direction that is moved
     * @param playerXY the players location
     * @return level a 2d array of chars containing the level
     */
    public char[][] movement(char[][] level, int[] modifier,int[] playerXY){
      
        int y = playerXY[0];
        int x = playerXY[1];
        int yModifier = modifier[0];
        int xModifier = modifier[1];
        
        if(level[y + yModifier][x + xModifier] != '#'){
            if(level[y + yModifier][x + xModifier] == ' ' && level[y][x] == '+'){
                level[y + yModifier][x + xModifier] = '@';
                level[y][x] = '.';
            }else if(level[y + yModifier][x + xModifier] == ' '){
                level[y + yModifier][x + xModifier] = '@';
                level[y][x] = ' ';  
            }else if(level[y + yModifier][x + xModifier] == '.'){
                level[y + yModifier][x + xModifier] = '+';
                if( level[y][x] == '@' && level[y + yModifier][x + xModifier] == '+'){
                    level[y][x] = ' ';
                }else if(level[y][x] == '+' && level[y + yModifier][x + xModifier] == '+'){
                    level[y][x] = '.';
                }
            }else if(level[y + yModifier][x + xModifier] == '$'){
                if(level[y + (yModifier*2)][x + (xModifier*2)] != '#' & level[y + (yModifier*2)][x + (xModifier*2)] != '$'  & level[y + (yModifier*2)][x + (xModifier*2)] == '.' & level[y][x] == '+'){
                    level[y + yModifier][x + xModifier] = '@';
                    level[y + (yModifier*2)][x + (xModifier*2)] = '*';
                    level[y][x] = '.';
                }else if(level[y + (yModifier*2)][x + (xModifier*2)] != '#' && level[y + (yModifier*2)][x + (xModifier*2)] != '$' && level[y + (yModifier*2)][x + (xModifier*2)] == ' ' && level[y][x] == '+'){
                    level[y + yModifier][x + xModifier] = '@';
                    level[y + (yModifier*2)][x + (xModifier*2)] = '$';
                    level[y][x] = '.';
                }else if(level[y + (yModifier*2)][x + (xModifier*2)] != '#' && level[y + (yModifier*2)][x + (xModifier*2)] != '$' && level[y + (yModifier*2)][x + (xModifier*2)] == '.'){
                    level[y + yModifier][x + xModifier] = '@';
                    level[y + (yModifier*2)][x + (xModifier*2)] = '*';
                    level[y][x] = ' ';
                }else if(level[y + (yModifier*2)][x + (xModifier*2)] != '#' && level[y + (yModifier*2)][x + (xModifier*2)] != '$' && level[y + (yModifier*2)][x + (xModifier*2)] == ' '){
                    level[y + yModifier][x + xModifier] = '@';
                    level[y + (yModifier*2)][x + (xModifier*2)] = '$';
                    level[y][x] = ' ';
                }
            }else if(level[y + yModifier][x + xModifier] == '*'){
                if(level[y + (yModifier*2)][x + (xModifier*2)] != '#' && level[y + (yModifier*2)][x + (xModifier*2)] != '$' && level[y + (yModifier*2)][x + (xModifier*2)] != '*' && level[y + (yModifier*2)][x + (xModifier*2)] == '.' && level[y][x] == '@'){
                    level[y + (yModifier*2)][x + (xModifier*2)] = '*';
                    level[y + yModifier][x + xModifier] = '+';
                    level[y][x] = ' ';
                }else if(level[y + (yModifier*2)][x + (xModifier*2)] != '#' && level[y + (yModifier*2)][x + (xModifier*2)] != '$' && level[y + (yModifier*2)][x + (xModifier*2)] != '*' && level[y + (yModifier*2)][x + (xModifier*2)] == '.' && level[y][x] == '+'){
                    level[y + (yModifier*2)][x + (xModifier*2)] = '*';
                    level[y + yModifier][x + xModifier] = '+';
                    level[y][x] = '.';
                }else if(level[y + (yModifier*2)][x + (xModifier*2)] != '#' && level[y + (yModifier*2)][x + (xModifier*2)] != '$' && level[y + (yModifier*2)][x + (xModifier*2)] != '*' && level[y + (yModifier*2)][x + (xModifier*2)] == ' ' && level[y][x] == '+' ){
                    level[y + (yModifier*2)][x + (xModifier*2)] = '$';
                    level[y + yModifier][x + xModifier] = '+';
                    level[y][x] = '.';
                }else if(level[y + (yModifier*2)][x + (xModifier*2)] != '#' && level[y + (yModifier*2)][x + (xModifier*2)] != '$' && level[y + (yModifier*2)][x + (xModifier*2)] != '*' && level[y + (yModifier*2)][x + (xModifier*2)] == ' ' && level[y][x] == '@'){
                    level[y + (yModifier*2)][x + (xModifier*2)] = '$';
                    level[y + yModifier][x + xModifier] = '+';
                    level[y][x] = ' ';
                }
            }
        }
        
        return level;
    }
    
}
