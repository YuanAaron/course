CHARGE =  [{key: 'F',value: '免费'},{key: 'C',value: '收费'}];

//相比刚添加的后端枚举，前端的枚举缺少枚举类型的信息，为了解决这个问题，前端枚举换成了下面的写法
//这样可以直接在前端代码中以SECTION_CHARGE.CHARGE.key的方式使用枚举值
SECTION_CHARGE = {FREE:{key: 'F',value: '免费'},CHARGE: {key: 'C',value: '收费'}};