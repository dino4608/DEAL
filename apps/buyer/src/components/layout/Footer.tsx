'use client';

import { app } from '@/app/utils';
import React from 'react';

const Footer: React.FC = () => {
    return (
        <div className="w-full bg-black text-white h-100 p-8 sm:p-12 lg:p-20">
            <div className="container h-full flex justify-center items-center mx-auto px-4 sm:px-20">
                <p>&copy; {new Date().getFullYear()} {app.name}. All rights reserved.</p>
            </div>
        </div>
    );
};

export default Footer;