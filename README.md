# SmartRail

# Description 

 Train Simulator with moving Train with which you can dynamically select the destination. The simulation implements a 
 DFS to find it's path with the assistance of a boolean field on each track object as opposed to a boolean array.
 
 # Set up
 
 To edit the layout of the track you would edit the configuration file found in the src folder. A station must be placed at 
 and end of the lane, while tracks, switches, and lights may lie anywhere in between. The terms urs, drs, uls, and dls refer
 to the direction of the switch; up-right, down-left, etc. A track will have the id of track, a light will have the id of light
 and the lane must be completed with a station on both ends.
 
 # Graphical Interface
 
 The GUI is responsive to which destination the train is moving to. You may not change the destination while the train 
 is moving and the GUI will not allow the train to move on a non existent path or to a non existent station.
 
 # Moving Forward
 
 We hope to implement threads on all portions of the project, unfortunately do to a misunderstanding in the project requirements
 and lack of time to change our current design this is not possible. Also more testing on the DFS might be required as of the moment,
 but a large model would reveal that.
 
 
