Some students from the college of business have decided that for their next big money making scheme, 
they are going to open a computer science themed amusement park called 7 flags, "--more --flags --more --CSpuns".
With rides Kingda Knuth, Blue Scream of Death, i386 Tower of Terror, and big spinning wheel GeForce.
the entrepreneurs have decided to leverage technology to make waiting time more bearable 
They propose a system called the "Flash Drive Pass" which allows a visitor to book a ride on one of the park's amusements on their smartphone.
Once the visitor has booked a ride, they are placed in a virtual line, where they wait until it's their turn.
Once they come to the front of the Virtual Queue, they are placed into a limited-size holding queue and notified that they have to show up in person for the ride. 
Once they show up in person, they have around 5 minutes to wait until the ride is available. 
More wealthy customers will be able to make especially good use of the system because, 
for a fee, a customer will be able to simultaneously be in up to 2 (silver plan) or 3 (gold plan) different queues at once.
If a customer is currently on a ride or in another holding queue when it is their turn to be dequeued,
they are simply placed at the back of the queue that they were going to be dequeued from. 
You will be implementing a simulation of the amusement park's queue system so that the entrepreneurs can fine-tune their system.



  
Simulation details: we will assume that every customer will always be in as many virtual lines as possible (this means that regular customers join a line the second they get off a ride, and Gold and Silver customers are always on the maximum number of lines possible). A customer is considered "on a virtual line" until he/she has entered the holding queue (akin to physically showing up for a ride). The customer is then holding or on the ride, and will not be available until they are finished with the ride. A person's status should indicate if they are simply waiting on a virtual line (available) or on a given ride (on ride or holding).  Once a customer is available, they choose with equal probability (if you do extra credit, this may change) one of the four rides to put themselves on a virtual line for (for gold and silver customers, it is possible they may want to take a ride multiple times, and therefore reserve multiple spots on the virtual line).  In order to determine how big the rides should be and how big the holding queues should be, you will be creating a simulation where these parameters can be modified, and the number of customers of each type that are attending on a given day. If a Virtual Line is empty, then a user that reserves for a ride will get placed straight on the Holding Queue (unless the user is currently in another holding queue or ride), and if that is empty, then the user can go straight onto the ride. If a customer shows up to a ride partway through the ride sequence, they should not be let on until after the current ride has finished. We can imagine that loading and unloading the rides is instant (so when one ride terminates, a new one begins with new customers on board).
