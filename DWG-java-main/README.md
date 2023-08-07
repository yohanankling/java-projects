# oop_Ex2 - Directed Weighted Graph

## Overview
This project implements a directed weighted graph and provides various functionalities related to graph manipulation and analysis. The main features of the algorithm include:

- Reading graphs from JSON files.
- Checking if the graph is strongly connected.
- Calculating the shortest path between nodes using Dijkstra's Algorithm.
- Saving the graph after applying certain algorithms to enhance its efficiency.
- Checking the Traveling Salesman Problem (TSP) with a Hamiltonian Circle.
- Calculating the center of the graph, which is the node closest to all other nodes.
- Displaying the graph in a graphical user interface (GUI) window with additional menu options.

## Running the Code
To run the code, download the "Ex2.jar" file and three JSON files provided. Execute the following command in the command prompt:

```
java -jar Ex2.jar G2.json
```

You can replace "G2.json" with any other JSON file in the same format as needed. Additionally, you have the option to add, remove, or modify nodes and edges within the code by running:

```
java -jar Ex2.jar
```

This will show the default graph "G1.json" as a starting point.

## Tests
The project includes some tests on the classes, which can be utilized for validation or reference.

## Work in Progress
Please note that the "shortestPathDist" function, used to calculate the shortest path between nodes, is still a work in progress and might not function correctly. As a result, the other algorithms that depend on it are also under development. Due to this ongoing development, a report and results for running time on large graphs (e.g., 10000, 100000 nodes) have not been provided yet.

Development Team:
- Tzach
- Yohanan

## GUI
The graphical program allows you to visualize and manipulate the graph. You can save or load graphs using the "SAVE\LOAD" button in the menu. To edit the graph's nodes and edges, press the "GRAPH" button, and to utilize the algorithms, use the "ALGORITHM" button.

## Screenshots
![Graph](https://user-images.githubusercontent.com/93263233/223714571-1538201e-ec5e-4d26-8163-8d1aebf30a9f.png)
![Shortest Path](https://user-images.githubusercontent.com/93263233/223714685-ed2cdd93-9770-4170-ac21-8f6f8e8312f3.png)
![Graph 3](https://user-images.githubusercontent.com/93263233/223714808-5d61bf76-7b07-4604-b6ba-e777f56681ed.png)
![Graph 4](https://user-images.githubusercontent.com/93263233/223715019-fd26bd3e-5ab4-4ee5-8bc1-4838e9200738.png)

Feel free to explore the project, use the GUI for graph visualization, and contribute to the ongoing development of the algorithms. Enjoy the features and functionalities provided by the project!
