import java.util.*;
class Graph {
    private Map<String, List<Edge>> adjList;
    public Graph() {
        adjList = new HashMap<>();
    }
    public void addVertex(String vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }
    public void addEdge(String src, String dest, int weight) {
        adjList.get(src).add(new Edge(dest, weight));
        adjList.get(dest).add(new Edge(src, weight)); // Bidirectional
    }
    public Map<String, Integer> dijkstra(String start) {
// Check if start vertex exists
        if (!adjList.containsKey(start)) {
            return new HashMap<>();
        }
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
// Initialize distances
        for (String vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            String currentVertex = current.vertex;
            if (visited.contains(currentVertex)) continue;
            visited.add(currentVertex);
// Check if current vertex has edges
            if (adjList.get(currentVertex) != null) {
                for (Edge edge : adjList.get(currentVertex)) {
                    String neighbor = edge.destination;
                    int newDist = distances.get(currentVertex) + edge.weight;
                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        previous.put(neighbor, currentVertex);
                        pq.offer(new Node(neighbor, newDist));
                    }
                }
            }
        }
        return distances;
    }
    public List<String> getPath(String start, String end) {
// Check if both locations exist
        if (!adjList.containsKey(start) || !adjList.containsKey(end)) {
            return new ArrayList<>();
        }
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        for (String vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            String currentVertex = current.vertex;
            if (visited.contains(currentVertex)) continue;
            visited.add(currentVertex);
            if (currentVertex.equals(end)) break;// Check if current vertex has edges
            if (adjList.get(currentVertex) != null) {
                for (Edge edge : adjList.get(currentVertex)) {
                    String neighbor = edge.destination;
                    int newDist = distances.get(currentVertex) + edge.weight;
                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        previous.put(neighbor, currentVertex);
                        pq.offer(new Node(neighbor, newDist));
                    }
                }
            }
        }
// Reconstruct path
        List<String> path = new ArrayList<>();
        String current = end;
        while (current != null) {
            path.add(0, current);
            current = previous.get(current);
        }
        return path.size() > 0 && path.get(0).equals(start) ? path : new ArrayList<>();
    }
}
class Edge {
    String destination;
    int weight;
    public Edge(String destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}
class Node implements Comparable<Node> {
    String vertex;
    int distance;
    public Node(String vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }@Override
    public int compareTo(Node other) {
        return Integer.compare(this.distance, other.distance);
    }
}
public class BangaloreRouteFinder {
    public static void main(String[] args) {
        Graph graph = new Graph();
// Add Bangalore locations
        String[] locations = {
                "Majestic", "MG Road", "Brigade Road", "Koramangala",
                "Indiranagar", "Whitefield", "Electronic City", "Banashankari",
                "Jayanagar", "Marathahalli", "HSR Layout", "BTM Layout"
        };
        for (String location : locations) {
            graph.addVertex(location);
        }
// Add routes with distances in km
        graph.addEdge("Majestic", "MG Road", 3);
        graph.addEdge("MG Road", "Brigade Road", 1);
        graph.addEdge("Brigade Road", "Indiranagar", 4);
        graph.addEdge("Indiranagar", "Koramangala", 6);
        graph.addEdge("Koramangala", "BTM Layout", 3);
        graph.addEdge("BTM Layout", "Jayanagar", 4);
        graph.addEdge("Jayanagar", "Banashankari", 5);
        graph.addEdge("Majestic", "Banashankari", 8);
        graph.addEdge("Koramangala", "HSR Layout", 3);
        graph.addEdge("HSR Layout", "Electronic City", 12);
        graph.addEdge("Indiranagar", "Marathahalli", 8);
        graph.addEdge("Marathahalli", "Whitefield", 10);
        graph.addEdge("MG Road", "Koramangala", 7);
        graph.addEdge("Brigade Road", "Koramangala", 5);
        Scanner sc = new Scanner(System.in);
        String start = "";
        String destination = "";
        boolean validInput = false;
        System.out.println("=== Bangalore Route Finder ===");// Loop until valid input is received
        while (!validInput) {
            System.out.println("\nAvailable locations:");
            for (int i = 0; i < locations.length; i++) {
                System.out.println((i + 1) + ". " + locations[i]);
            }
// Get starting location with validation
            boolean validStart = false;
            while (!validStart) {
                System.out.print("\nEnter starting location: ");
                start = sc.nextLine().trim();
                if (isValidLocation(start, locations)) {
                    validStart = true;
                } else {
                    System.out.println("❌ Invalid location: '" + start + "'");
                    System.out.println("Please choose from the list above (case-sensitive)");
                }
            }
// Get destination with validation
            boolean validDest = false;
            while (!validDest) {
                System.out.print("Enter destination: ");
                destination = sc.nextLine().trim();
                if (isValidLocation(destination, locations)) {
                    if (!destination.equals(start)) {
                        validDest = true;
                        validInput = true;
                    } else {
                        System.out.println("❌ Destination cannot be same as starting location!");
                        System.out.println("Please enter a different destination.");
                    }
                } else {
                    System.out.println("❌ Invalid location: '" + destination + "'");
                    System.out.println("Please choose from the list above (case-sensitive)");
                }
            }
        }
// Find shortest path
        List<String> path = graph.getPath(start, destination);Map<String, Integer> distances = graph.dijkstra(start);
        if (path.isEmpty() || distances.isEmpty()) {
            System.out.println("\n❌ ERROR: Unable to process route!");
        } else if (!distances.containsKey(destination) || distances.get(destination) == Integer.MAX_VALUE) {
            System.out.println("❌ No route found between " + start + " and " + destination);
        } else {
            System.out.println("\n✅ === SHORTEST ROUTE FOUND ===");
            System.out.println("From: " + start + " → To: " + destination);
            System.out.println("Total Distance: " + distances.get(destination) + " km");
            System.out.println("Route: " + String.join(" → ", path));
            System.out.println("\n📊📊 === ALL DISTANCES FROM " + start + " ===");
            distances.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> {
                        if (entry.getValue() != Integer.MAX_VALUE) {
                            System.out.println(entry.getKey() + ": " + entry.getValue() + " km");
                        }
                    });
        }
        System.out.println("\n💡💡 Thank you for using Bangalore Route Finder!");
        sc.close();
    }
    // Helper method to validate location input
    private static boolean isValidLocation(String location, String[] validLocations) {
        for (String validLoc : validLocations) {
            if (validLoc.equals(location)) {
                return true;
            }
        }
        return false;
    }
}
