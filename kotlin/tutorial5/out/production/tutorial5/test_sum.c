int sum(int a, int b) {
  return a + b;
}
long expectedValue = 64000;
int result = sum(32000,32000);
assert(result, expectedValue) // no testable
assert((long)result, expectedValue) // testable

assert((b>0) && (a > (INT_MAX - b))) //testable
assert((b<0) && (a < (INT_MIN - b))) //testable

// Best solution
// UT - external behaviour
// Only - my functional behaviour
typdef struct result_type {
   int value;
   int error;
} RESULT
int sum(signed int si_a, signed int si_b) {
   signed int result;
   if(((si_b > 0) && (si_a > (INT_MAX -si_b))) ||
      ((si_b < 0) && (si_a < (INT_MIN - si_b)))) {
        //Either throw exception or return struct
   } else {
      result = si_a + si_b
   }
   return result
}

RESULT sum(signed int si_a, signed int si_b) {
   signed RESULT result;
   if(((si_b > 0) && (si_a > (INT_MAX -si_b))) ||
      ((si_b < 0) && (si_a < (INT_MIN - si_b)))) {
        //Either throw exception or return struct
        result.error = -1;
        result.value = 0;
   } else {
      result.value = si_a + si_b
      result.error = 0;
   }
   return result
}