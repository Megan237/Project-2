import java.util.List;

/**
 * This class will implement the First Come, First Served
 * disk scheduling algorithm.
 * 
 * SamGross MeganStinefield
 */
public class FCFS implements IDiskAlgorithm {

	@Override
	public int calculateDistance(List<DiskRequest> requests, int headPosition) {

		int totalDistance = 0;
		int currentPosition = headPosition;
		while (!requests.isEmpty()){
			int nextPositionToMoveTo = requests.get(0).getTrack();
			totalDistance += Math.abs(currentPosition - nextPositionToMoveTo);
			currentPosition = nextPositionToMoveTo;
			requests.remove(0);
		}

		return totalDistance;
	}

}
