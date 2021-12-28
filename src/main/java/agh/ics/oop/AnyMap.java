package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AnyMap implements IPositionChangeObserver{
    public Map<Vector2d, Grass> grasses = new HashMap<>();
    public Map<Vector2d, ArrayList<Animal>> animals = new HashMap<>();
    public int grassNumber = 0;
    public int animalNumber = 0;

    public ArrayList<Animal> animalsAt(Vector2d position) {
        return this.animals.get(position);
    }

    public void place(Animal animal) {
        Vector2d position = animal.getPosition();

        ArrayList<Animal> list = this.animals.get(position);
        if (list == null){
            list = new ArrayList<>();
            list.add(animal);
            this.animals.put(position,list);
        }
        else{
            list.add(animal);
        }
        this.animalNumber++;
        animal.addObserver(this);
    }


    public boolean isOccupiedAnimal(Vector2d position) {
        if (this.animals.get(position) == null){
            return false;
        }
        return !this.animals.get(position).isEmpty();
    }
    public boolean isOccupiedGrass(Vector2d position){
        return this.grasses.get(position) != null;
    }

    public Vector2d[] border() {
        return new Vector2d[] {new Vector2d(0,0),new Vector2d(Constants.width,Constants.height)};
    }

    public void positionChanged(Animal moveAnimal, Vector2d oldPosition, Vector2d newPosition) {
        ArrayList<Animal> list = this.animalsAt(oldPosition);
        list.remove(moveAnimal);
        list = this.animalsAt(newPosition);
        if (list == null){
            list = new ArrayList<>();
            this.animals.put(newPosition,list);
        }
        list.add(moveAnimal);
    }

    public abstract Vector2d moveAttempt(Vector2d new_position);

    public Vector2d freePosition() {
        while(true){
            int x = Constants.random.nextInt(Constants.width);
            int y = Constants.random.nextInt(Constants.height);
            Vector2d shot = new Vector2d(x,y);
            if (!isOccupiedAnimal(shot) && !isOccupiedGrass(shot)){
                return shot;
            }
        }
    }

    public boolean isJungle(Vector2d position) {
        int jungleSize = Constants.width*Constants.height*Constants.jungleRatio/100;
        int jungleWidth = (int)Math.sqrt(jungleSize);
        int offsetX = (Constants.width - jungleWidth)/2;
        int offsetY = (Constants.height - jungleWidth)/2;
        int x = position.x;
        int y = position.y;

        if (x >= offsetX && y >= offsetY){
            if(x <= Constants.width-offsetX && y <= Constants.height-offsetY) {
                return true;
            }
        }
        return false;
    }

    public void removeAnimal(Animal animal) {
        Vector2d position = animal.getPosition();
        ArrayList<Animal> list = this.animalsAt(position);
        if (!list.remove(animal))
        {
            throw new IllegalStateException();
        }
        this.animalNumber -= 1;
    }

    public void removeGrass(Vector2d position) {
        this.grassNumber -= 1;
        this.grasses.remove(position);
    }

    public void createGrass() {
        if(this.grassNumber + this.animalNumber < Constants.width*Constants.height*0.5){    //only do random if there isnt much grass
            while(true) {
                int x = Constants.random.nextInt(Constants.width);
                int y = Constants.random.nextInt(Constants.height);
                Vector2d shot = new Vector2d(x, y);
                if (this.isOccupiedAnimal(shot) || this.isOccupiedGrass(shot)) {
                    continue;
                }
                Grass newGrass = new Grass(shot);
                this.grassNumber += 1;
                this.grasses.put(shot, newGrass);
                break;
            }
        }
        else{
            boolean created = false;
            for(int i = 0; i < Constants.height && !created;i++){
                for (int j = 0 ;j < Constants.width && !created;j++){
                    Vector2d position = new Vector2d(i,j);
                    if (!this.isOccupiedGrass(position) && !this.isOccupiedAnimal(position) ){
                        Grass newGrass = new Grass(position);
                        this.grasses.put(position,newGrass);
                        this.grassNumber += 1;
                        created = true;
                    }
                }
            }
        }

        int attemt = 0;
        int jungleSize = Constants.width*Constants.height*Constants.jungleRatio/100;
        int jungleWidth = (int)Math.sqrt(jungleSize);
        int offsetX = (Constants.width - jungleWidth)/2;
        int offsetY = (Constants.height - jungleWidth)/2;

        boolean created = false;
        while(attemt < 15 && !created){
            attemt += 1;
            int x = Constants.random.nextInt(jungleWidth) + offsetX+1;
            int y = Constants.random.nextInt(jungleWidth) + offsetY+1;
            Vector2d shot = new Vector2d(x,y);
            if(!this.isOccupiedAnimal(shot) && !this.isOccupiedGrass(shot)){
                Grass newGrass = new Grass(shot);
                this.grasses.put(shot,newGrass);
                this.grassNumber += 1;
                created = true;
            }
        }
        if (!created){
            for(int i = offsetY; i < Constants.height - offsetY+1 && !created;i++){
                for (int j = offsetX ;j < Constants.width - offsetX+1 && !created;j++){
                    Vector2d position = new Vector2d(i,j);
                    if (!this.isOccupiedGrass(position) && !this.isOccupiedAnimal(position)){
                        Grass newGrass = new Grass(position);
                        this.grasses.put(position,newGrass);
                        this.grassNumber += 1;
                        created = true;
                    }
                }
            }
        }
    }
}
