# I2CS_Ex2 - Maze Solving with OOP

This project is part of the **Introduction to Computer Science** course at **Ariel University**. 
The objective is to implement the `Map2D` and `Pixel2D` interfaces to create maze-solving algorithms based on the **Breadth-First Search (BFS)** approach. The project includes a GUI visualization implemented using `StdDraw`.
the project is coded on Java


##  Key Concepts
This assignment demonstrates understanding of:
- **Object-Oriented Programming**: usage of getters, setters, and interface implementation.
- **Algorithm Design**: Implementation of BFS for pathfinding and flood fill.
- **File Handling**: Reading and writing map data.
- **GUI Integration**: Visualizing algorithms using standard drawing libraries.

---

##  Implemented Methods

### 1. Getters and Setters
Includes various getters and setters for manipulating specific maps and pixels.

### 2. Drawing Methods
Three primary drawing methods were created to visualize shapes on the map:
- `drawLine`
- `drawCircle`
- `drawRect`

<p align="center">
  <img width="350" height="500" alt="Drawing Example" src="https://github.com/user-attachments/assets/58086ad8-ad54-47c2-9dde-cbdf034ec551" />
</p>

### 3. Flood Fill (`fill`)
I implemented a flood fill algorithm that allows the user to color specific clusters of pixels. 
- **Logic**: It uses an iterative BFS approach (Queue-based). It identifies a pixel's neighbors belonging to the same cluster, adds them to a queue, and processes them until the entire area is colored.
- **Note**: An older, recursive version of this method is preserved as a comment at the bottom of the `Map` class.

### 4. Shortest Path (`shortestPath`)
The `shortestPath` method finds the shortest distance from point A to point B while avoiding obstacles. It uses the BFS algorithm.
It explores the grid layer by layer using a queue, marking visited nodes and recording the "parent" of each neighbor.
It handles cyclic boundaries if the cyclic flag is true.
Once the destination is reached, the method reconstructs the path by backtracking from the destination to the start using the parent references.
Finally, the list is reversed to return the path in the correct order.

<p align="center">
  <img width="350" height="500" alt="Shortest Path Example" src="https://github.com/user-attachments/assets/3b912d88-25f9-4656-92af-4fac41507075" />
</p>

### 5. All Distances (`allDistance`)

The `allDistance` method creates a new map where every pixel stores the distance from the start point to itself. This is effectively a "flood fill" that maps out how far every point is from the start.
It creates a copy of the map and initializes it. The private method paddMap2D resets all valid (non-obstacle) pixels to a value of -1 to mark them as "unvisited".
It processes pixels one by one.
For every neighbor, it checks if it is inside the bounds (handling cyclic wrapping if needed).
If a neighbor has a value of -1 (meaning it hasn't been visited yet), its value is updated to current_distance + 1.
It returns a Map2D object containing these distance values.

![bfs_all_distance](https://github.com/user-attachments/assets/3153a84f-1a55-4d37-b9d0-e2915f87aa7b)

---

## 🔗 References
[Assignment Guide (Google Docs)](https://docs.google.com/document/d/1BtSldHciAGqjccYC3d7BKvWqIljfxKeJ/edit?usp=sharing&ouid=114201356376601981370&rtpof=true&sd=true)



