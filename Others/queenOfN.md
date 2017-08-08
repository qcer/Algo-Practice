**递归版本实现**

    var aux = null;
    var N = 0;
    var count = 0;
    function backtrack(deep) {
        // body...
        if (deep > N) {
            count += 1;
            // console.log(aux);
        }else{
            for (var i = 1; i < N+1; i++) {
                aux[deep] = i;
                if (check(deep)) {backtrack(deep+1)};
            }
        }
    }
    function check(k) {
        // body...
        for (var i = 1; i < k; i++) {
            if (Math.abs(i-k) === Math.abs(aux[i]-aux[k]) || aux[i] === aux[k]) {
                return false;
            }
        }
        return true;
    }
    function queenOfN(n) {
        // body...
        N = n;
        aux = (new Array(N+1)).fill(0);
        backtrack(1);
    }


**迭代版本实现**

    var aux = [];
    var deep = 1;
    var N = 0;
    var count = 0;
    function queenOfN(n) {
        // body...
        N = n;
        aux = (new Array(N+2)).fill(0);
        while(deep > 0){
            if (deep > n) {
                // console.log(aux);
                count++;
                deep--;
            }else{
                aux[deep] += 1;
                while(!check(deep) && aux[deep] <= n){
                    aux[deep] += 1;
                }
                if (aux[deep] <= n) {
                    deep++;
                    aux[deep] = 0;
                }else{
                    deep--;
                }
            }
        }
    }
    function check(k) {
        // body...
        for (var i = 1; i < k; i++) {
            if (Math.abs(i-k) === Math.abs(aux[i]-aux[k]) || aux[i] === aux[k]) {
                return false;
            }
        }
        return true;
    }


**测试用例**

    queenOfN(1) //console.log(count)->1
    queenOfN(2) //console.log(count)->0
    queenOfN(3) //console.log(count)->0
    queenOfN(4) //console.log(count)->2
    queenOfN(8) //console.log(count)->92
    queenOfN(14) //console.log(count)->365596