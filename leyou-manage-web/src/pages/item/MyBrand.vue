<template>
  <v-card>
    <v-card-title>
      Nutrition
      
  
      
    </v-card-title>
    <v-card>
      <v-card-title>
        <v-layout row wrap>
          <v-flex >
            <v-btn color="primary" @click="callBrandForm">新增品牌</v-btn>
          </v-flex>
          <v-flex offset-xs6 >
            <v-text-field
              v-model="search"
              append-icon="search"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-flex>

        </v-layout>
        
        <!-- <v-spacer></v-spacer> -->
        

      </v-card-title>
      
    </v-card>

    
    <v-divider/>
    <v-data-table
      :headers="headers"
      :items="brands"
      :search="search"
      :pagination.sync="pagination"
      :total-items="totalBrands"
      :loading="loading"
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td>{{ props.item.id }}</td>
        <td class="text-xs-center">{{ props.item.name }}</td>
        <td class="text-xs-center"><img :src="props.item.image"></td>
        <td class="text-xs-center">{{ props.item.letter }}</td>
        <td class="justify-center layout">
          <v-btn color="info">编辑</v-btn>
          <v-btn color="warning">删除</v-btn>
        </td>
        
      </template>

      
    </v-data-table>
  </v-card>
</template>

<script>
  export default {
    name: "my-brand",
    data() {
      return {
        search: '', // 搜索过滤字段
        totalBrands: 0, // 总条数
        brands: [], // 当前页品牌数据
        loading: true, // 是否在加载中
        pagination: {}, // 分页信息
        headers: [
          {text: 'id', align: 'center', value: 'id'},
          {text: '名称', align: 'center', sortable: false, value: 'name'},
          {text: 'LOGO', align: 'center', sortable: false, value: 'image'},
          {text: '首字母', align: 'center', value: 'letter', sortable: true,},
          {text: '操作', align: 'center', value: 'edit', sortable: false,}
        ]
      }
    },
    watch: {
      pagination: { 
        deep: true,
        handler() {
          this.getDataFromServer();
        }
       
      }
    },
    mounted(){ // 渲染后执行
      // 查询数据
      this.getDataFromServer();
    },
    methods:{
      callBrandForm(){
          // alert("hello")

      },
      getDataFromServer(){ 
          this.$http.get("/item/brand/list",{
            params:{
              key:this.search,
              page:this.pagination.page,
              rows:this.pagination.rowsPerPage,
              sortBy:this.pagination.sortBy,
              desc:this.pagination.descending
            }
          }).then(resp => {
            this.
            this.brands = resp.data.items;
            this.totalBrands = resp.data.total;
            this.loading = false;
          })
        
      }
    }
  }
</script>

<style scoped>

</style>