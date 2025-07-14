# Shortest Path Finder using Dijkstra's Algorithm

This project implements a **Shortest Path Finder** using **Dijkstra’s Algorithm** in Java. It models a real-world urban network and computes the shortest route between two locations, similar to GPS navigation systems.

## Project Overview

Urban navigation presents challenges like complex road networks and varying traffic conditions. This project uses **graph theory** and **Dijkstra’s algorithm** to compute optimal paths between locations in Bangalore’s city network. 

The implementation provides:
- A graph-based representation of locations and routes
- An interactive console interface for user input and result display
- Realistic distance modeling for validation

---

## Features

- Find shortest paths between major Bangalore locations
- Displays total distance and the path taken
- Lists all distances from a selected starting point
- Validates user input to avoid errors
- Modular architecture for future enhancements

---

## Technologies Used

- **Java**
- **Data Structures:** HashMap, PriorityQueue, Adjacency List
- Graph Theory concepts
- Algorithm design (Greedy method for shortest path)

---

## Project Structure

```
src/
│
├── Graph.java                 # Graph representation and Dijkstra’s implementation
├── Edge.java                  # Represents edges with destination and weight
├── Node.java                  # Helper class for PriorityQueue
└── BangaloreRouteFinder.java  # Main class with user interface
```

---

## How It Works

1. Users select a **starting location** and **destination** from a predefined list.
2. Dijkstra’s algorithm calculates the shortest route and total distance.
3. Results are displayed in an interactive console.

Example:
```
=== Bangalore Route Finder ===
Available locations:
1. Majestic
2. MG Road
3. Brigade Road
...

Enter starting location: Majestic
Enter destination: Jayanagar

✅ === SHORTEST ROUTE FOUND ===
From: Majestic → To: Jayanagar
Total Distance: 13 km
Route: Majestic → Banashankari → Jayanagar
```

---

## Sample Locations

- Majestic
- MG Road
- Brigade Road
- Koramangala
- Indiranagar
- Whitefield
- Electronic City
- Banashankari
- Jayanagar
- Marathahalli
- HSR Layout
- BTM Layout

---

## Future Enhancements

- Real-time traffic integration
- Dynamic routing
- Support for multiple optimization criteria (time, fuel, etc.)
- GUI-based interface
- Integration with external maps APIs (Google Maps, OpenStreetMap)

---

## Getting Started

1. Clone this repository:
    ```bash
    git clone https://github.com/Aditya.Kandari/shortest-path-finder-project.git
    ```
2. Compile the Java files:
    ```bash
    javac BangaloreRouteFinder.java
    ```
3. Run the program:
    ```bash
    java BangaloreRouteFinder
    ```

---

*Thank you for using Bangalore Route Finder!*
