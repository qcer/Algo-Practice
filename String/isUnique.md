### *字符判重*
#### *问题描述*
给定一字符串str,由a-z或者A-Z之间组成,判断字符串中是否存在重复的字符。

    function isUnique(str) {
        // body...
        var boolen_ary =  new Array(58).fill(false),
            length = str.length,
            curr_index = 0;
        for (let i = 0; i < length; i++) {
            curr_index = str[i].charCodeAt()-65;
            if (boolen_ary[curr_index]) {
                return false;
            }
            boolen_ary[curr_index] = true;
        }
        return true;
    }