class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        double result = 0;
        int result1 = 0;
        int count1 = 0;
        int count2 = 0;
        int mid = length / 2;
        int limit = 0;

        if ((length % 2) == 0)
            limit = mid + 1;
        else
            limit = mid + 2;

        if (nums1.length == 0) {// nums1 empty
            if ((length % 2) == 0) {
                result = (double) (nums2[mid - 1] + nums2[mid]) / 2;
                return result;
            } else
                return nums2[mid];
        }

        if (nums2.length == 0) {// nums2 empty
            if ((length % 2) == 0) {
                result = (double) (nums1[mid - 1] + nums1[mid]) / 2;
                return result;
            } else
                return nums1[mid];
        }

        if ((nums1.length == 1) && (nums2.length == 1)) {// both length is 1
            return (double) (nums1[0] + nums2[0]) / 2;
        }

        for (int i = 0; i < limit; i++) {

            if ((count1 < nums1.length) && (count2 < nums2.length)) {

                if (nums1[count1] < nums2[count2]) {
                    result = result1;
                    result1 = nums1[count1];
                    count1++;
                } else {
                    result = result1;
                    result1 = nums2[count2];
                    count2++;
                }

            } else if (nums1.length <= count1) {

                result = result1;
                result1 = nums2[count2];
                count2++;

            } else if (nums2.length <= count2) {

                result = result1;
                result1 = nums1[count1];
                count1++;

            }
        }

        if ((length % 2) == 0) {// even
            result = (result + result1) / 2;
        }

        return result;
    }
}