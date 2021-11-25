package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {

    private MoveDirection[] moves;
    private ArrayList<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] moves, IWorldMap mapa, Vector2d[] pozycje) {
        this.moves = moves;
        for (Vector2d start_position : pozycje) {
            Animal animal_to_place = new Animal(mapa, start_position);
            animals.add(animal_to_place);
            mapa.place(animal_to_place);
        }
    }

    public void run() {
        int n = animals.size();
        int total_moves = moves.length;
        for (int i = 0; i < total_moves; i++) {
            int animal_to_move = i % n;
            animals.get(animal_to_move).move(moves[i]);
        }
    }
}
