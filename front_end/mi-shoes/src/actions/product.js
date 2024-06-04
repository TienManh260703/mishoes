export const createProduct = (productData) => {
  return {
    type: "CREATE_PRODUCT",
    data: productData,
  };
};

export const updateProduct = (productId, productUpdates) => {
  return {
    type: "UPDATE_PRODUCT",
    id: productId,
    data: productUpdates,
  };
};

export const deleteProduct = (productId) => {
  return {
    type: "DELETE_PRODUCT",
    id: productId,
  };
};