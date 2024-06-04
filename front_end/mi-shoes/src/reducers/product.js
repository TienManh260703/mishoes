const initialState = [];

const productReducer = (state = initialState, action) => {
  switch (action.type) {
    case "CREATE_PRODUCT":
      return [...state, action.data];
    case "UPDATE_PRODUCT":
      return state.map((product) =>
        product.id === action.id ? { ...product, ...action.data } : product
      );
    case "DELETE_PRODUCT":
      return state.filter((product) => product.id !== action.id);
    default:
      return state;
  }
};

export default productReducer;