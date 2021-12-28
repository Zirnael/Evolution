package agh.ics.oop;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Map;

public class Simulation implements Runnable{
    public AnyMap mapa;
    ArrayList<Animal> animals = new ArrayList<>();
    private final MapDrawer drawer;
    private int lives;

    public Simulation(AnyMap map,MapDrawer drawer){
        this.mapa = map;
        this.drawer = drawer;
        this.lives = Constants.magic ? 3 : 0;

        for(int i= 0; i < Constants.noAnimals;i++){
            int[] genes = new int[32];
            for(int j= 0 ; j < 32; j++){
                genes[j] = Constants.random.nextInt(8);
            }
            Animal newAnimal = new Animal(this.mapa,this.mapa.freePosition(),genes,Constants.startEnergy);
            this.animals.add(newAnimal);
            this.mapa.place(newAnimal);
        }
    }
    @Override
    public void run() {
        try{
            while(true){
                this.removeDead();
                this.moveAnimals();
                this.eat();
                this.reproduce();
                this.createGrass();
                this.magic();
                Platform.runLater(this.drawer::draw);
                if(Thread.interrupted())
                {
                    throw new InterruptedException();
                }
                Thread.sleep(Constants.dayDelay);
            }
        }
        catch (InterruptedException ignored){
        }
    }

    private void magic() throws InterruptedException {
        if(this.animals.size()<=5 && this.lives>0){
            ArrayList<Animal> toAdd = new ArrayList<>();
            for(Animal animal:this.animals){
                Animal newAnimal = animal.copy();
                newAnimal.position = this.mapa.freePosition();
                toAdd.add(animal);
            }
            for(Animal animal:toAdd){
                this.animals.add(animal);
                this.mapa.place(animal);
            }
            this.lives--;
            Platform.runLater(()->{
                Alert a = new Alert(Alert.AlertType.INFORMATION,"There are"+this.lives+" magic tricks left");
                a.show();
            });
            Thread.sleep(5000);
        }
    }

    private void removeDead() {
        ArrayList<Animal> toRemove = new ArrayList<>();

        int n = this.animals.size();
        for(Animal animal:this.animals){
            if(animal.energy <= 0){
                toRemove.add(animal);
            }
        }
        for(Animal animal:toRemove){
            this.animals.remove(animal);
            this.mapa.removeAnimal(animal);
        }
    }

    private void createGrass() {
        this.mapa.createGrass();
    }


    private void moveAnimals() {
        for (Animal animal : this.animals) {
            animal.move();
            animal.energy -= Constants.moveEnergy;
        }
    }

    private void eat() {
        for(Animal animal:this.animals){
            Vector2d position = animal.getPosition();
            if (this.mapa.isOccupiedGrass(position)){
                ArrayList<Animal> list = this.mapa.animalsAt(position);
                int biggestEnergy = 0;
                // find who gets to eat
                for(Animal animal1:list){
                    biggestEnergy = Math.max(biggestEnergy,animal1.energy);
                }
                // count the ones who get to eat
                int count = 0;
                for(Animal animal1:list){
                    if(animal1.energy == biggestEnergy){
                        count += 1;
                    }
                }
                if(count == 0){
                    return;
                }
                // the ones who get to eat eat
                for(Animal animal1:list){
                    animal1.energy += Constants.plantEnergy/count;
                }
                this.mapa.removeGrass(position);
            }
        }
    }

    private void reproduce() {
        ArrayList<Animal> toAdd = new ArrayList<>();
        for(Animal animal:this.animals){
            Vector2d position = animal.getPosition();
            ArrayList<Animal> list = this.mapa.animalsAt(position);
            int biggestEnergy1 = 0;
            int biggestEnergy2 = 0;
            for(Animal animal1:list){
                if (animal1.energy > biggestEnergy2){
                    biggestEnergy2 = animal1.energy;
                    if(biggestEnergy2 > biggestEnergy1)
                    {
                        int t = biggestEnergy2;
                        biggestEnergy2 = biggestEnergy1;
                        biggestEnergy1 = t;
                    }
                }
            }
            Animal firstReproducer = null;
            Animal secondReproducer = null;
            if (biggestEnergy2 == biggestEnergy1){
                for(Animal animal1:list){
                    if (animal1.energy == biggestEnergy2){
                        if (firstReproducer == null){
                            firstReproducer = animal1;
                        }else if (secondReproducer == null){
                            secondReproducer = animal1;
                        }else{
                            break;
                        }

                    }
                }
            }
            else{
                for(Animal animal1:list){
                    if (animal1.energy == biggestEnergy1){
                        firstReproducer = animal1;
                    }
                    if (animal1.energy == biggestEnergy2){
                        secondReproducer = animal1;
                    }
                }
            }
            if (firstReproducer != null && secondReproducer != null && firstReproducer.energy >= Constants.startEnergy/2 && secondReproducer.energy>=Constants.startEnergy/2){
                this.reproduction(firstReproducer,secondReproducer,toAdd);
            }
        }
        for(Animal animal: toAdd){
            this.mapa.place(animal);
            this.animals.add(animal);
        }
    }

    private void reproduction(Animal firstReproducer, Animal secondReproducer, ArrayList<Animal> toAdd) {
        Vector2d position = firstReproducer.getPosition();
        int firstEnergy = firstReproducer.energy;
        int secondEnergy = secondReproducer.energy;
        int totalEnergy = firstEnergy + secondEnergy;

        Animal leftAnimal;
        Animal rightAnimal;
        // Randomly choose left animal
        if (Constants.random.nextBoolean())
        {
            leftAnimal = firstReproducer;
            rightAnimal = secondReproducer;
        }
        else{
            leftAnimal = secondReproducer;
            rightAnimal = firstReproducer;
        }
        int cutPoint = leftAnimal.energy * 31 / totalEnergy;
        int[] genes = new int[32];
        for(int i= 0; i < cutPoint;i++){
            genes[i] = leftAnimal.genes[i];
        }
        for(int i= cutPoint; i< 32; i++){
            genes[i] = rightAnimal.genes[i];
        }
        totalEnergy = 0;
        totalEnergy += firstReproducer.energy / 4;
        firstReproducer.energy -= firstReproducer.energy/4;
        totalEnergy += secondReproducer.energy/4;
        secondReproducer.energy -= secondReproducer.energy/4;
        Animal baby = new Animal(this.mapa,position,genes,totalEnergy);
        toAdd.add(baby);
    }
}
