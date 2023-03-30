package persistence;

import org.json.JSONObject;

// an interface
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}






//REFERENCE:
// Build software better, together. GitHub. (n.d.). Retrieved March 7, 2023,
// from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo