import { combineReducers } from "redux";
import productReducer from "./product";


const allReducer = combineReducers({
    productReducer,
})

export default allReducer;