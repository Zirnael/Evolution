package agh.ics.oop;

import javax.lang.model.type.NullType;

public class World {
    public static void run(Directions[] tablica) {
        System.out.println("Start");
        for (Directions c:tablica) {
            switch (c){
                case f:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case b:
                    System.out.println("Zwierzak idzie do tylu");
                    break;
                case l:
                    System.out.println("Zwierzak idzie w lewo");
                    break;
                case r:
                    System.out.println("Zwierzak idzie w prawo");

            }
        }
        System.out.println("Stop");
    }
    public static void main(String[] args) {
        System.out.println("system wystartował");
        String test = args[0];
        char[] cha = test.toCharArray();
        int n = cha.length;
        int count = 0;
        for (int i = 0; i < n; i++){
            if (cha[i] == 'f' || cha[i] == 'b' || cha[i] == 'l' || cha[i] == 'r')
                count += 1;
        }
        Directions[] to_give = new Directions[count];
        count = 0;
        for(char c: cha){
            Directions to_add;
            switch (c) {
                case 'f':
                    to_add = Directions.f;
                    break;
                case 'b':
                    to_add = Directions.b;
                    break;
                case 'l':
                    to_add = Directions.l;
                    break;
                case 'r':
                    to_add = Directions.r;
                    break;
                default:
                    to_add = null;
            };
            if(to_add != null){
                to_give[count] = to_add;
                count += 1;
            }
        }

        run(to_give);
        System.out.println("system zakończył działanie");
    }
}
