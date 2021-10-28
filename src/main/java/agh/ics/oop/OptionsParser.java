package agh.ics.oop;

public class OptionsParser {
    public MoveDirection[] parse(String[] tekst)
    {
        int count = 0;
        for(String x: tekst)
        {
            switch (x){
                case "f":
                case "forward":
                case "b":
                case "backward":
                case "r":
                case "right":
                case "l":
                case "left":
                {
                    count++;
                    break;
                }
            }
        }
        MoveDirection[] result = new MoveDirection[count];
        count = 0;
        for(String x: tekst)
        {
            MoveDirection to_add = null;
            switch (x){
                case "f":
                case "forward":
                {
                    to_add = MoveDirection.FORWARD;
                    break;
                }
                case "b":
                case "backward":
                {
                    to_add = MoveDirection.BACKWARD;
                    break;
                }
                case "r":
                case "right":
                {
                    to_add = MoveDirection.RIGHT;
                    break;
                }
                case "l":
                case "left":
                {
                    to_add = MoveDirection.LEFT;
                    break;
                }
            }
            if (to_add != null)
            {
                result[count++] = to_add;
            }
        }

        return result;
    }
}
