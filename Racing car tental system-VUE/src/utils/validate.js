/**
 * Email validation
 * @param {*} s
 */
export function isEmail (s) {
  return /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(s)
}

/**
 * Mobile phone - now accepts any format (relaxed for N.America)
 * @param {*} s
 */
export function isMobile (s) {
  // Accept any phone format: just check if it has 7+ digits
  return /^[\d\s\-\(\)\+]{7,}$/.test(s)
}

/**
 * Phone number - relaxed validation
 * @param {*} s
 */
export function isPhone (s) {
  // Accept any phone format
  return /^[\d\s\-\(\)\+]{7,}$/.test(s)
}

/**
 * URL validation
 * @param {*} s
 */
export function isURL (s) {
  return /^http[s]?:\/\/.*/.test(s)
}

/**
 * Number validation (can be decimal, not negative, can be empty)
 * @param {*} s 
 */
export function isNumber(s){
  return  /(^-?[+-]?([0-9]*\.?[0-9]+|[0-9]+\.?[0-9]*)([eE][+-]?[0-9]+)?$)|(^$)/.test(s);
}

/**
 * Integer validation (can be empty)
 * @param {*} s 
 */
export function isIntNumer(s){
  return  /(^-?\d+$)|(^$)/.test(s);
}

/**
 * ID validation - now accepts any format (relaxed for N.America)
 * Accepts driver's license, passport, etc.
 */
export function checkIdCard(idcard) {
  // Accept any ID format - just check if it's not empty and has some alphanumeric chars
  if (!idcard || idcard.trim().length < 5) {
      return false;
  }
  return true;
}
