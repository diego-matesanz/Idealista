package com.diego.matesanz.idealista.ui.screens.productList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.recyclerview.widget.RecyclerView
import com.diego.matesanz.idealista.databinding.ProductItemBinding
import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.ui.theme.IdealistaTheme

class ProductListAdapter(
    private val products: List<ProductItem>,
    private val listener: ProductItemListener,
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ProductItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
            onClick = listener::onProductClick,
            onFavoriteClick = listener::onFavoriteClick,
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    class ViewHolder(
        val binding: ProductItemBinding,
        private val onClick: (String) -> Unit,
        private val onFavoriteClick: (ProductItem) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductItem) {
            binding.composeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    IdealistaTheme {
                        ProductItem(
                            product = product,
                            onClick = onClick,
                            onFavoriteClick = onFavoriteClick,
                        )
                    }
                }
            }
        }
    }
}
