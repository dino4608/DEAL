import { ShoppingCart } from "lucide-react";
import Link from "next/link";

export const Cart = () => {
    return (
        <Link
            href={'/cart'}
            className='relative'
        >
            <ShoppingCart />
            <span className='
                        absolute -top-2 -right-4 w-6 h-4 rounded-full bg-[var(--dino-red-1)] text-white text-xs font-semibold
                        flex items-center justify-center'>
                68
            </span>
        </Link>
    );
};