public class RecursionFun {
	// Precondition: k >= 1 and n >= k
	public int combinations(int n, int k) {
		if (k == 1)
			return n;
		if (k == n)
			return 1;
		return combinations(n - 1, k - 1) + combinations(n - 1, k);
	}

	// Precondition: n >= 0
	public int factorial(int n) {
		if (n == 0 || n == 1)
			return 1;
		return n * factorial(n - 1);
	}

	// Precondition: n >= 1
	public double addReciprocals(int n) {
		if (n == 1)
			return 1.0;
		return (1.0 / n) + addReciprocals(n - 1);
	}

	// Precondition: m and n are not both 0. Neither can be negative.
	public int GCD(int m, int n) {
		if (n == 0)
			return m;
		return GCD(n, m % n);
	}

	// Precondition: n > 0 && n <= 40 (takes a long time when n > 40)
	public int fibonacci(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public String underScore(String str) {
		if (str.length() <= 1)
			return str;
		if (str.charAt(0) == str.charAt(1))
			return "" + str.charAt(0) + "_" + underScore(str.substring(1));
		return "" + str.charAt(0) + underScore(str.substring(1));
	}

	public boolean nestParen(String str) {
		if (str.length() == 1)
			return false;
		if (str.length() == 0)
			return true;
		if (str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')')
			return nestParen(str.substring(1, str.length() - 1));
		return false;
	}

	public String noAdjacents(String str) {
		if (str.length() == 0)
			return "";
		if (str.length() == 1)
			return str;
		if (str.charAt(0) == str.charAt(1))
			return "" + noAdjacents(str.substring(1));
		return "" + str.charAt(0) + noAdjacents(str.substring(1));
	}

	// Precondition: num >= 0
	public String convert(int num, int base) {
		if (num < base)
			return "" + num;
		return "" + convert(num / base, base) + num % base;
	}

	// Precondition: n >= 0
	public String intWithCommas(int n) {
		if (n < 1000)
			return "" + n;
		else if (n % 1000 < 10)
			return intWithCommas(n / 1000) + ",00" + n % 1000;
		else if (n % 1000 < 100)
			return intWithCommas(n / 1000) + ",0" + n % 1000;
		return intWithCommas(n / 1000) + "," + n % 1000;
	}

	// Preconditions: nums.length >= 1, beginIndex < nums.length, endIndex <
	// nums.length
	public int sumArray(int[] nums, int beginIndex, int endIndex) {
		if (beginIndex > endIndex)
			return 0;
		return nums[beginIndex] + sumArray(nums, beginIndex + 1, endIndex);
	}

	public int sumArray(int[] nums) {
		return sumArray(nums, 0, nums.length - 1);
	}

	// Preconditions: beginIndex == 0 and rightIndex == nums.length-1
	public void reverseArray(int[] nums) {
		reverseArray(nums, 0, nums.length - 1);
	}

	private void reverseArray(int[] nums, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int temp = nums[leftIndex];
			nums[leftIndex] = nums[rightIndex];
			nums[rightIndex] = temp;
			reverseArray(nums, leftIndex + 1, rightIndex - 1);
		}
	}

	// Precondition: nums.length > 0 and the array is always filled
	public int arrayRange(int[] nums) {
		return arrayRange(nums, 0, nums[0], nums[0]);
	}

	private int arrayRange(int[] nums, int index, int min, int max) {
		if (index > nums.length - 1)
			return max - min;

		int newMin = min;
		int newMax = max;
		if (nums[index] < min)
			newMin = nums[index];
		if (nums[index] > max)
			newMax = nums[index];
		return arrayRange(nums, index + 1, newMin, newMax);
	}

	// Precondition: The array is always filled
	public boolean isSorted(int[] nums) {
		return isSorted(nums, 0);
	}

	private boolean isSorted(int[] nums, int index) {
		if (index >= nums.length - 1)
			return true;
		if (nums[index] > nums[index + 1])
			return false;
		return isSorted(nums, index + 1);
	}

	// Precondition: The array is always filled
	public boolean found(String search, String[] strs) {
		return found(search, strs, 0);
	}

	private boolean found(String search, String[] strs, int index) {
		if (index > strs.length - 1)
			return false;
		if (strs[index].equals(search))
			return true;
		return found(search, strs, index + 1);
	}

	// Precondition: strings.length > 0 (no empty arrays), strings[] is sorted
	public int binarySearch(String searchValue, String[] strings) {
		return binarySearch(searchValue, strings, 0, strings.length - 1);
	}

	private int binarySearch(String searchValue, String[] strings, int min, int max) {
		if (min > max)
			return -1;
		int middle = (min + max) / 2;
		if (strings[middle].compareTo(searchValue) == 0)
			return middle;
		else if (strings[middle].compareTo(searchValue) < 0)
			return binarySearch(searchValue, strings, middle + 1, max);
		else
			return binarySearch(searchValue, strings, min, middle - 1);
	}
}
