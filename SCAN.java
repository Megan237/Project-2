import java.util.ArrayList;
import java.util.List;

/**
 * This class will implement the SCAN 
 * disk scheduling algorithm.
 * 
 * @author ...
 */
public class SCAN implements IDiskAlgorithm {

	@Override
	public int calculateDistance(List<DiskRequest> requests, int headPosition) {
		int totalDistance = 0;
		int currentHeadPosition = headPosition;


		// Sort requests based on track number
		requests.sort((a, b) -> Integer.compare(a.getTrack(), b.getTrack()));


		List<DiskRequest> requestsToRemove = new ArrayList<>();
		// Go right (towards the end) from the initial head position and track the completed requests
		for (DiskRequest request : requests) {
			if (request.getTimeOfArrival() <= totalDistance && request.getTrack() >= headPosition) {
				totalDistance += Math.abs(currentHeadPosition - request.getTrack());
				currentHeadPosition = request.getTrack();
				requestsToRemove.add(request);
			}
		}

		//remove requests from the
		for (DiskRequest toRemove : requestsToRemove){
			requests.remove(toRemove);
		}
		if (requests.isEmpty())
			return totalDistance;
		else {
			totalDistance += Math.abs(currentHeadPosition - 4999);
			currentHeadPosition = 4999;
		}

		requestsToRemove = new ArrayList<>();
		requests.sort((b, a) -> Integer.compare(b.getTrack(), a.getTrack()));

		for (DiskRequest request : requests) {
			if (request.getTimeOfArrival() <= totalDistance && request.getTrack() <= currentHeadPosition) {
				totalDistance += Math.abs(currentHeadPosition - request.getTrack());
				currentHeadPosition = request.getTrack();
				requestsToRemove.add(request);
			}
		}

		//remove requests from the
		for (DiskRequest toRemove : requestsToRemove){
			requests.remove(toRemove);
		}


		//IF there are more requests still, we go to the end and reset
		if (!requests.isEmpty()) {
			totalDistance += Math.abs(0 - currentHeadPosition);
			for (DiskRequest request: requests){
				request.setTimeOfArrival(request.getTimeOfArrival() - totalDistance);
			}
			totalDistance += calculateDistance(requests, 0);
		}

		return totalDistance;
	}

}
