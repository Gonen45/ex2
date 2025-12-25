# Ex02 - Maze soloving with OOP 

This project is an assignment from the Java programing course *Introduction to Computer Science* in Arial University.  
The goal is to implement various the Map2D and Pixel2D interfaces for Maze soloving algorithms based on BFS eproch.
and presntition of them on GUI implention from StdDraw.


The project demonstrates my understanding of:
- getters and setters
- Mathods creation
- BFS algorithm
- working with files
  

##  Implemented Mathods

### 1. getters and setters
varius getters and setters for change and gain specific maps and pixels.

### 2. drawing Mathods
I made three drawing Mathods: drawLine, drawCircle and drawRect.
for drawing shapes over the map I working on.

<img width="350" height="500" alt="Screenshot 2025-12-25 212115" src="https://github.com/user-attachments/assets/58086ad8-ad54-47c2-9dde-cbdf034ec551" />


### 3. fill Mathod
I implented the flood fill algohritm for abalen the user to color
each cluster of pixels in the same color.
I based it on BFS logic I took each pixel neighboors that is from the same cluster and add it to the queue for checking is neigboors and coloring him.
this mathod has an older version in the botoom of Map has a comment, the older version works with recurcion.

### 4. shortestPath
The shortestPath method finds the shortest distance from point A to point B while avoiding obstacles. It uses the BFS algorithm.
It explores the grid layer by layer using a queue, marking visited nodes and recording the "parent" of each neighbor.
It handles cyclic boundaries if the cyclic flag is true.
Once the destination is reached, the method reconstructs the path by backtracking from the destination to the start using the parent references.
Finally, the list is reversed to return the path in the correct order.

<img width="350" height="500" alt="image" src="https://github.com/user-attachments/assets/3b912d88-25f9-4656-92af-4fac41507075" />



### 5.allDistance
The allDistance method creates a new map where every pixel stores the distance from the start point to itself. This is effectively a "flood fill" that maps out how far every point is from the start.
It creates a copy of the map and initializes it. The private method paddMap2D resets all valid (non-obstacle) pixels to a value of -1 to mark them as "unvisited".
It processes pixels one by one.
For every neighbor, it checks if it is inside the bounds (handling cyclic wrapping if needed).
If a neighbor has a value of -1 (meaning it hasn't been visited yet), its value is updated to current_distance + 1.
It returns a Map2D object containing these distance values.



link for the assigment guide: https://docs.google.com/document/d/1BtSldHciAGqjccYC3d7BKvWqIljfxKeJ/edit?usp=sharing&ouid=114201356376601981370&rtpof=true&sd=true
