package com.mycompany.myapp;

import java.util.ArrayList;


public class GameObjectCollection implements ICollection {
	
	private ArrayList<GameObject> objectList;
	private int shipCount, asteroidCount, missileCount, stationCount;

	public GameObjectCollection() {
		objectList = new ArrayList<GameObject>();
		shipCount = 0;
		asteroidCount = 0;
		missileCount = 0;
		stationCount = 0;
	}

	public int getShipCount() {
		return shipCount;
	}
	
	public int getAsteroidCount() {
		return asteroidCount;
	}
	

	public int getMissileCount() {
		return missileCount;
	}
	

	public int getStationCount() {
		return stationCount;
	}
	
	
	public  GameObjectIterator getIterator() {
        GameObjectIterator gameItr = new GameObjectIterator(objectList);
        return gameItr;
	}
	
	/*
	 * Adds GameObject to ArrayList
	 * checks type of GameObject was added
	 * Increments count based on type
	 */
	public void add(GameObject o) {
		objectList.add(o);
		if (o instanceof Asteroid)
			asteroidCount++;
		else if( o instanceof Ship)
			shipCount++;
		else if(o instanceof Missile)
			missileCount++;
		else if(o instanceof SpaceStation)
			stationCount++;
	}
	
	/*
	 * Takes class type as parameter (Asteroid.class, Ship.class, etc)
	 * iterates through ArrayList and removes first instance of that class type
	 */
	public void removeObject(Class<?> cls) {
		GameObjectIterator it =  this.getIterator();
		boolean removed = false;
		while(!removed && it.hasNext()) {
			if(cls.isInstance(it.getNext()) ) {
				it.remove();
				removed = true;
			}
		}
	}
	
    
    
	private class GameObjectIterator implements IIterator<GameObject> {

        private int current = 0, next = 0;

        public GameObjectIterator(ArrayList<GameObject> go) {
            objectList = go;
        }

        public boolean hasNext() {
            return next < objectList.size();
        }


        /*
         * Checks what type of object is at the current location in the array
         * decrements the count for that type
         * removes the object from the array
         */
        public void remove() {
        	GameObject go = objectList.get(current); ;
    		if (go instanceof Asteroid)
    			asteroidCount--;
    		else if( go instanceof Ship)
    			shipCount--;
    		else if(go instanceof Missile)
    			missileCount--;
    		else if(go instanceof SpaceStation)
    			stationCount--;
            objectList.remove(current);
        }

		@Override
		public GameObject getNext() {
            current = next;
            GameObject currentObj = objectList.get(current);
            next += 1;
            return currentObj;
		}

    }

}



























