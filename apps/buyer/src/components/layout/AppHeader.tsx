import { Cart } from "../header/Cart";
import { Logo } from "../header/Logo";
import { NavLeft } from "../header/NavLeft";
import { NavRight } from "../header/NavRight";
import { SearchBar } from "../header/SearchBar";

export const AppHeader = () => {

    // TODO: adjust LeftNavigation, RightNavigation to simpler

    // REFERENCED: height = padding y + text + border = 16*2 + 32 + 0.8 = 65px
    return (
        <header className='
            bg-white w-full px-2 sm:px-6 lg:px-12 xl:px-20 py-2 sm:py-4
            border-b border-gray-200 shadow-sm backdrop-blur-sm
            sticky top-0 z-50'
        >
            <div className='h-8 container mx-auto flex justify-between items-center text-back font-medium'>
                <Logo />

                <NavLeft />

                <SearchBar />

                <NavRight />

                <Cart />
            </div>
        </header>
    );
};