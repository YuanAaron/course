/**
 * 数组过滤器 例如：{{CHARGE | optionKV(section.charge)}}
 * @param list 例如：[{key: 'F',value: '免费'},{key: 'C',value: '收费'}]
 * @param key 例如：C
 * @returns {string} 例如：免费
 */
let optionKV = function (list, key) {
  if (!list || !key) {
    return "";
  } else {
    let result = "";
    for (let i = 0; i < list.length; i++) {
      if (key === list[i]["key"]) {
        result = list[i]["value"];
      }
    }
    return result;
  }
};

export default {
  optionKV
}

